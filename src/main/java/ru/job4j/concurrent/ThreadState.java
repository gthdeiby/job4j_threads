package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println("First - " + first.getState());
        System.out.println("Second - " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println("First - " + first.getState());
            System.out.println("Second - " + second.getState());
        }
        System.out.println("First - " + first.getState());
        System.out.println("Second - " + second.getState());
        System.out.println("Work completed!");
    }
}
