package ru.job4j.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool;

    public EmailNotification(ExecutorService pool) {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = String.format("Notification {username} to email {email}.",
                        user.getUsername(), user.getEmail());
                String body = String.format("Add a new event to {username}.",
                        user.getUsername());
                String email = user.getEmail();
                send(subject, body, email);
            }
        });
    }

    void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
