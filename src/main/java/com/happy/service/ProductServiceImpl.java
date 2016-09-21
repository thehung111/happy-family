package com.happy.service;

import com.happy.exception.PositiveIntegerException;
import com.happy.model.Product;
import com.happy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{


    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;

    }

    @Override
    public int getMilkQty() {
        Product prod = productRepository.findOne(1);
        return prod.getMilkQty();
    }

    @Override
    public int getEggsQty() {
        Product prod = productRepository.findOne(1);
        return prod.getEggQty();
    }

    @Override
    public int buyMilk(int qty) throws PositiveIntegerException{
        if(qty <= 0 )
            throw new PositiveIntegerException();

        Product prod = productRepository.findOne(1);
        int curMilkQty = prod.getMilkQty();
        int purchasedQty = Math.min(qty, curMilkQty);

        // update
        prod.setMilkQty( curMilkQty - purchasedQty);
        productRepository.save(prod);

        return purchasedQty;
    }

    @Override
    public int buyEggs(int qty) throws PositiveIntegerException{
        if(qty <= 0 )
            throw new PositiveIntegerException();

        Product prod = productRepository.findOne(1);
        int curEggQty = prod.getEggQty();
        int purchasedQty = Math.min(qty, curEggQty);

        // update
        prod.setEggQty( curEggQty - purchasedQty);
        productRepository.save(prod);

        return purchasedQty;
    }
}
