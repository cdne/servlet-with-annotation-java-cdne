package com.codecool.webshop;

import java.util.HashSet;
import java.util.Iterator;

public class Stock {
    HashSet<Item> stockSet = new HashSet<>();

    public Item getProductById(int id){
        System.out.println(id);
        for(Item item : stockSet){
            if(item.getId() == id){
                return item;
            }
        }

        return null;
    }

}
