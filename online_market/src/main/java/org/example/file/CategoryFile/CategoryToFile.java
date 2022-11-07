package org.example.file.CategoryFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Category;

import java.io.*;

public class CategoryToFile {
    //static String headUrl = "MyFile/";

    public static Category addCategory(Category category, String headUrl) throws IOException {
        File file = new File(headUrl + "/"+category.getName()+"/" + category.getId() + ".txt");
        file.createNewFile();
        if (file.)
        for (File file1:file.listFiles()){
            if (file1.getName().equals(category.getName())){
                return null;
            }
        }
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String s=gson.toJson(category);
       FileOutputStream fileOutputStream=new FileOutputStream(file,true);
       fileOutputStream.write(s.getBytes());
       fileOutputStream.close();
       return category;
    }

    public static boolean delete(String headUrl, int categoryId) {
        return false;
    }

    public static Category getById(String headUrl, int categoryId) {
        return null;
    }

    public static void showList() {

    }
}
