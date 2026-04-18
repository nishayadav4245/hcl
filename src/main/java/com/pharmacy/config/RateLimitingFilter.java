package com.pharmacy.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, RateLimitInfo> requestCounts = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 100;
    private static final long TIME_WINDOW_MS = 60_000;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        cleanupStaleEntries();

        String clientIp = request.getRemoteAddr();
        RateLimitInfo rateLimitInfo = requestCounts.compute(clientIp, (key, existing) -> {
            long now = System.currentTimeMillis();
            if (existing == null || now - existing.windowStart > TIME_WINDOW_MS) {
                return new RateLimitInfo(now, new AtomicInteger(1));
            }
            existing.count.incrementAndGet();
            return existing;
        });

        if (rateLimitInfo.count.get() > MAX_REQUESTS) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"success\":false,\"message\":\"Rate limit exceeded. Try again later.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void cleanupStaleEntries() {
        long now = System.currentTimeMillis();
        requestCounts.entrySet().removeIf(entry ->
                now - entry.getValue().windowStart > TIME_WINDOW_MS);
    }

    private static class RateLimitInfo {
        final long windowStart;
        final AtomicInteger count;

        RateLimitInfo(long windowStart, AtomicInteger count) {
            this.windowStart = windowStart;
            this.count = count;
        }
    }
}
