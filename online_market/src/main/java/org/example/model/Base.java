package org.example.model;

import lombok.Data;
import org.example.DataBase;

import java.io.IOException;

@Data
public abstract class Base {
    private int id;
    public Base() {
            this.id = DataBase.idGeneration++;
        try {
            DataBase.writeIdToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
