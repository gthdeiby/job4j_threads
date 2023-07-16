package ru.job4j.threadlocal;

public class FirstThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.tl.set("Это поток 1.");
        System.out.println(ThreadLocalDemo.tl.get());
    }
}