package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int index = 0;
        var process = new char[] {'-', '\\', '|', '/'};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                System.out.print("\r load: " + process[index++]);
                index = index == process.length ? 0 : index;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
