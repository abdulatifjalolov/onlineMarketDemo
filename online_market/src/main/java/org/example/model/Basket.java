package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Basket extends Base{
    private  List<Integer> productIdList=new ArrayList<>();
    private Long chatId;

}
