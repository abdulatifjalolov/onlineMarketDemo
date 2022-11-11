package org.example.dto;


import org.example.model.Product;

import java.util.Scanner;

public class ProductDto {
    public static Product addProduct(Product product) {
        System.out.println(" ENTER NAME OF PRODUCT ");
        product.setName(new Scanner(System.in).nextLine());

        System.out.println(" ENTER MODEL OF PRODUCT ");
        product.setModel(new Scanner(System.in).nextLine());

        System.out.println(" ENTER BRAND OF PRODUCT ");
        product.setBrand(new Scanner(System.in).nextLine());

        System.out.println(" ENTER PRICE OF PRODUCT ");
        product.setPrice(new Scanner(System.in).nextDouble());

        System.out.println(" ENTER COUNT OF PRODUCT ");
        product.setCount(new Scanner(System.in).nextInt());

        System.out.println(" ENTER DISCOUNT OF PRODUCT (%)");
        product.setDiscount(new Scanner(System.in).nextInt());

        System.out.println("ENTER CATEGORY ID OF PRODUCT");
        product.setCategoryId(new Scanner(System.in).nextInt());

        System.out.println("ENTER PHOTO URI  ");
        product.setUri(new Scanner(System.in).nextLine());

        return product;
    }

    public static Product update(Product product) {
        int var = 10;
        while (var != 0) {
            System.out.println("1.NAME 2.MODEL 3.BRAND 4.PRICE 5.COUNT 6.DISCOUNT 7.CATEGORY ID 8.EDIT URI 0.EXIT");
            var = new Scanner(System.in).nextInt();

            switch (var) {
                case 1 -> {
                    System.out.println("ENTER NAME ");
                    product.setName(new Scanner(System.in).nextLine());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 2 -> {
                    System.out.println("ENTER MODEL ");
                    product.setModel(new Scanner(System.in).nextLine());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 3 -> {
                    System.out.println("ENTER BRAND ");
                    product.setBrand(new Scanner(System.in).nextLine());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 4 -> {
                    System.out.println("ENTER PRICE ");
                    product.setPrice(new Scanner(System.in).nextDouble());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 5 -> {
                    System.out.println("ENTER COUNT ");
                    product.setCount(new Scanner(System.in).nextInt());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 6 -> {
                    System.out.println("ENTER DISCOUNT");
                    product.setDiscount(new Scanner(System.in).nextInt());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 7->{
                    System.out.println("ENTER CATEGORY ID");
                    product.setCategoryId(new Scanner(System.in).nextInt());
                    System.out.println("SUCCESSFULLY EDITED");
                }
                case 8->{
                    System.out.println("ENTER URI");
                    product.setUri(new Scanner(System.in).nextLine());
                    System.out.println("SUCCESSFULLY EDITED");
                }

            }
        }
        return product;

    }
}
