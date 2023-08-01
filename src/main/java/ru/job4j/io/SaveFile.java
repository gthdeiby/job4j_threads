package ru.job4j.io;

import java.io.*;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
