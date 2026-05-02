package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore<T> {
    private final Map<String, T> map = new HashMap<>();

    public void save(String id, T value) {
        map.put(id, value);
    }

    public T get(String id) {
        return map.get(id);
    }

    public List<T> values() {
        return new ArrayList<>(map.values());
    }

    public T remove(String id) {
        return map.remove(id);
    }

    public boolean contains(String id) {
        return map.containsKey(id);
    }
}
