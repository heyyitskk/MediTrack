package com.airtribe.meditrack.interfaces;

public interface Searchable<T> {
    T searchById(String id);

    T searchByName(String name);

    default boolean exists(String id) {
        return searchById(id) != null;
    }
}
