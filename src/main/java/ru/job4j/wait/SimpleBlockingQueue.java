package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxSize;

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == maxSize) {
                wait();
            }
            queue.offer(value);
            notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
        }
        T rsl = queue.poll();
        notify();
        return rsl;
    }
}