package ru.job4j.pool;

public class Job implements Runnable {
    private final Integer number;

    public Job(Integer number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(number);
    }
}
