package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Integer> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            int data;
            while ((data = reader.read()) > 0) {
                if (filter.test(data))
                output.append((char) data);
            }
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode(Predicate<Integer> filter) throws IOException {
        return getContent(ch -> ch < 0x80);
    }
}
