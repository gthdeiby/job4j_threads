package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    public void whenParallelIncrement() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(
            () -> {
                IntStream.range(0, 5).forEach(i -> casCount.increment());
            }
        );
        Thread second = new Thread(
            () -> {
                IntStream.range(0, 5).forEach(i -> casCount.increment());
            }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get()).isEqualTo(10);
    }
}