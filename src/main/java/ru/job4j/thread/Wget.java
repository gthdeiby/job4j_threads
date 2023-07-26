package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    public static void validateArgs(String[] args) throws MalformedURLException, URISyntaxException {
        if (args.length != 2) {
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
        var file = new File("tmp.xml");
        try (var in = new URL(url).openStream();
            var out = new FileOutputStream(file)) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            var startAt = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                var downloadAt = System.currentTimeMillis();
                out.write(dataBuffer, 0, bytesRead);
                if (downloadAt - startAt < speed) {
                    Thread.sleep(speed - downloadAt + startAt);
                }
                startAt = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException, URISyntaxException {
        validateArgs(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}