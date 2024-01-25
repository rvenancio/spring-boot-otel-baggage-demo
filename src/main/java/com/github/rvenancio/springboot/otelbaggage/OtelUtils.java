package com.github.rvenancio.springboot.otelbaggage;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rvenancio
 */
public final class OtelUtils {

    private OtelUtils() {
    }

    public static Map<String, String> getAll() {
        return Baggage.fromContext(Context.current())
                .asMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getValue()));
    }
}
