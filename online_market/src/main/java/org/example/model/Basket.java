package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Basket{
    private List<Integer> productIdList;
    private Long chatId;
}
