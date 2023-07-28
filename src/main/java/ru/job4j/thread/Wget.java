package ru.job4j.thread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget implements Runnable {
    private static final int TIME = 1000;
    private final String url;
    private final int speed;
    private static String fileName;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    public static void validateArgs(String[] args) throws MalformedURLException, URISyntaxException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Argument pattern violation");
        }

        if (Integer.parseInt(args[1]) < 0) {
            throw new IllegalArgumentException("The speed must be greater than zero");
        }

        isValidURL(args[0]);
    }

    public static void isValidURL(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (var in = new URL(url).openStream();
            var out = new FileOutputStream(fileName)) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            int bytesCount = 0;
            var startAt = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                bytesCount += bytesRead;
                out.write(dataBuffer, 0, bytesRead);
                if (bytesCount > speed) {
                    var speedTime = System.currentTimeMillis() - startAt;
                    if (speedTime < TIME) {
                        Thread.sleep(TIME - speedTime);
                    }
                    bytesCount = 0;
                    startAt = System.currentTimeMillis();
                }


            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException, URISyntaxException {
        validateArgs(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}