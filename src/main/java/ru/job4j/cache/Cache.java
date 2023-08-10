package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> biFunction = (key, value) -> {
            if (value.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base updated = new Base(key, value.getVersion() + 1);
            updated.setName(model.getName());
            return updated;
        };
        return memory.computeIfPresent(model.getId(), biFunction) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base getBase(int key) {
        return memory.get(key);
    }
}