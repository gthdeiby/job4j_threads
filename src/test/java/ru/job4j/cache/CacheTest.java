package ru.job4j.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    private Cache cache;
    private Base base;
    private Base anotherBase;

    @BeforeEach
    public void init() {
        cache = new Cache();
        base = new Base(1, 1);
        anotherBase = new Base(2, 1);
        cache.add(base);
        cache.add(anotherBase);
    }


    @Test
    public void whenAdd() {
        assertThat(cache.getBase(1)).isEqualTo(base);
        assertThat(cache.getBase(5)).isNull();
    }

    @Test
    public void whenNotAdd() {
        assertThat(cache.add(new Base(1, 2))).isFalse();
    }

    @Test
    public void whenUpdate() {
        Base model = new Base(2, 1);
        model.setName("Model");
        assertThat(cache.update(model)).isTrue();
        assertThat(cache.getBase(2).getName()).isEqualTo("Model");
        assertThat(cache.getBase(2).getVersion()).isEqualTo(2);
    }

    @Test
    public void whenNotUpdate() {
        assertThatThrownBy(() -> cache.update(new Base(2, 2)))
                .isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenDelete() {
        cache.delete(base);
        assertThat(cache.getBase(1)).isNull();
    }
}