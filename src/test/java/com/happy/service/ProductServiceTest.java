package com.happy.service;

import com.happy.exception.PositiveIntegerException;
import com.happy.model.Product;
import com.happy.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Hung on 21/9/16.
 */
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(productRepository);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMilkQty() {
        assertNotNull(productRepository);

        int milkQty = 100;

        Product mockProd = new Product();
        mockProd.setStoreId(1);
        //mockProd.setEggQty(20);
        mockProd.setMilkQty(milkQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);

        assertEquals("getMilkQty return wrong result", productService.getMilkQty() , milkQty );
    }

    @Test
    public void testGetEggsQty() {
        assertNotNull(productRepository);

        int eggQty = 10;

        Product mockProd = new Product();
        mockProd.setStoreId(1);
        mockProd.setEggQty(eggQty);
        //mockProd.setMilkQty(milkQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);

        assertEquals("getEggsQty return wrong result", productService.getEggsQty() , eggQty );
    }

    @Test(expected = PositiveIntegerException.class)
    public void testBuyMilkNegativeAmount() throws PositiveIntegerException {
        assertNotNull(productRepository);
        int milkQty = 10;

        Product mockProd = new Product();
        mockProd.setStoreId(1);
        //mockProd.setEggQty(20);
        mockProd.setMilkQty(milkQty);

        when(productRepository.findOne(1)).thenReturn(mockProd);

        productService.buyMilk(0);

    }

    @Test
    public void testBuyMilk() throws PositiveIntegerException {
        assertNotNull(productRepository);
        int milkQty = 10;

        Product mockProd = new Product();
        mockProd.setStoreId(1);
        mockProd.setEggQty(20);
        mockProd.setMilkQty(milkQty);

        when(productRepository.findOne(1)).thenReturn(mockProd);

        assertEquals( productService.buyMilk(1), 1 );

        // test max amount
        milkQty = 8;
        mockProd.setMilkQty(milkQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);
        assertEquals(  productService.buyMilk(milkQty) , milkQty  );

        // test buy more
        milkQty = 8;
        mockProd.setMilkQty(milkQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);
        assertEquals(  productService.buyMilk( milkQty + 2) , milkQty  );

    }


    @Test(expected = PositiveIntegerException.class)
    public void testBuyEggsNegativeAmt() throws PositiveIntegerException {
        assertNotNull(productRepository);

        int eggQty = 10;

        Product mockProd = new Product();
        mockProd.setStoreId(1);
        mockProd.setEggQty(eggQty);
        //mockProd.setMilkQty(milkQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);

        productService.buyMilk(0);
    }

    @Test
    public void testBuyEggs() throws PositiveIntegerException {
        assertNotNull(productRepository);
        int eggQty = 10;

        Product mockProd = new Product();
        mockProd.setStoreId(1);
        mockProd.setEggQty(eggQty);
        mockProd.setMilkQty(10);

        when(productRepository.findOne(1)).thenReturn(mockProd);

        assertEquals( productService.buyEggs(1), 1 );

        // test max amount
        eggQty = 8;
        mockProd.setEggQty(eggQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);
        assertEquals(  productService.buyEggs(eggQty) , eggQty  );

        // test buy more
        eggQty = 8;
        mockProd.setEggQty(eggQty);
        when(productRepository.findOne(1)).thenReturn(mockProd);
        assertEquals(  productService.buyEggs(eggQty + 2) , eggQty  );

    }

}