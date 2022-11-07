package org.example.model;

import lombok.Data;
import org.example.file.FileUtils;

import java.io.File;
import java.io.IOException;

@Data
public abstract class Base {
    static String headUrl = "C:/Users/abdulatif/forJAVA/online_market/";
    private static int idGeneration;

    static {
        try {
            idGeneration = FileUtils.readIdFromFile(headUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  int id;
    public Base() {
        this.id=idGeneration++;
        try {
            FileUtils.writeIdToFile(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
