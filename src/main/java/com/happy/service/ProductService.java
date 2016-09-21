package com.happy.service;

import com.happy.exception.PositiveIntegerException;
import com.happy.model.Product;

public interface ProductService {
    int getMilkQty();
    int getEggsQty();

    int buyMilk(int qty) throws PositiveIntegerException;
    int buyEggs(int qty) throws PositiveIntegerException;


}
