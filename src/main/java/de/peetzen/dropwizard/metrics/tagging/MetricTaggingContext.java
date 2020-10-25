package de.peetzen.dropwizard.metrics.tagging;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Holds tag information in a {@link ThreadLocal} context.
 */
public class MetricTaggingContext {

    private static final ThreadLocal<Map<String, String>> threadLocalTags = ThreadLocal.withInitial(() -> new HashMap<>());

    public static void put(String tagName, String tagValue) {
        Objects.requireNonNull(tagName, "tag name missing");
        Objects.requireNonNull(tagValue, "tag value missing");

        Map<String, String> tags = threadLocalTags.get();
        tags.put(tagName, tagValue);
    }

    public static Map<String, String> get() {
        return threadLocalTags.get();
    }

    public static boolean isEmpty() {
        return threadLocalTags.get().isEmpty();
    }

    public static void clear() {
        threadLocalTags.remove();
    }
}
