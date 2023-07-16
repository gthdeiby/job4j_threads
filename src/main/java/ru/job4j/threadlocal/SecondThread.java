package ru.job4j.threadlocal;

public class SecondThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.tl.set("Это поток 2.");
        System.out.println(ThreadLocalDemo.tl.get());
    }
}
