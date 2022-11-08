package org.example.model;

import lombok.Data;
import org.example.file.FileUtils;

import java.io.IOException;

@Data
public abstract class Base {
    private static int idGeneration;

    static {
        try {
            idGeneration = FileUtils.readIdFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int id;

    public Base() {
        this.id = idGeneration++;
        try {
            FileUtils.writeIdToFile(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
