package com.github.rvenancio.springboot.otelbaggage;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DemoFilter extends OncePerRequestFilter implements Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(DemoFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("### DemoFilter start ###");

        Baggage baggage = Baggage.builder().put("otel.baggage.demo.key", "this-is-a-value").build();

        try (Scope ignored = baggage.makeCurrent()) {
            LOG.info("Baggage inside DemoFilter try-with-resources: {}", OtelUtils.getAll());
            filterChain.doFilter(request, response);
            LOG.info("Baggage inside DemoFilter try-with-resources after 'filterChain.doFilter': {}", OtelUtils.getAll());
        }

        LOG.info("### DemoFilter end ###");
    }

    @Override
    public int getOrder() {
        return -15;
    }
}
