package ru.job4j.thread;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    public static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Argument pattern violation");
        }

        if (Integer.parseInt(args[1]) < 0) {
            throw new IllegalArgumentException("The speed must be greater than zero");
        }
    }

    @Override
    public void run() {
        /* Скачать файл*/
    }

    public static void main(String[] args) throws InterruptedException {
        validateArgs(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}