package cucumber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContext {

    // Thread-safe map in case of parallel executions
    private final Map<String, Object> scenarioContext;

    // Constructor initializes the map, using ConcurrentHashMap for thread safety
    public ScenarioContext() {
        this.scenarioContext = new ConcurrentHashMap<>();
    }

    /**
     * Adds a key-value pair to the context.
     *
     * @param key   the key (can be a String, Enum, etc.)
     * @param value the value to be stored
     */
    public <T> void setContext(String key, T value) {
        scenarioContext.put(key, value);
    }

    /**
     * Retrieves the value for the provided key and expected type.
     *
     * @param key  the key to retrieve the value
     * @param type the expected class type of the value
     * @param <T>  the generic type
     * @return the value cast to the appropriate type, or null if not found or type mismatch
     */
    public <T> T getContext(String key, Class<T> type) {
        Object value = scenarioContext.get(key);
        if (value != null && type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    /**
     * Checks if the context contains the given key.
     *
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    public boolean containsKey(String key) {
        return scenarioContext.containsKey(key);
    }

    /**
     * Clears the scenario context.
     */
    public void clearContext() {
        scenarioContext.clear();
    }
}
