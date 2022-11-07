package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base{
    private String name;
    private String brand;
    private double price;
    private String model;
    private int count;
    private int discount;
    private int categoryId;
}
