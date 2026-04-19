package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();
    private final AtomicLong counter = new AtomicLong(1000);

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String nextId(String prefix) {
        return prefix + counter.getAndIncrement();
    }
}
