package com.przemo.concurrency.forkjoinpool;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
    public List<Product> generate (int size) {
        List<Product> ret = new ArrayList<>();
        for (int i = 0; i<size;i++){
            Product p = new Product();
            p.setName("Product" + i);
            p.setPrice(10 + i);
            ret.add(p);
        }
        return ret;
    }
}
