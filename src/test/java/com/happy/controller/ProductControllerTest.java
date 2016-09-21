package com.happy.controller;

import com.happy.exception.PositiveIntegerException;
import com.happy.model.Product;
import com.happy.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

//@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ProductController(productService);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testGetMilkQty() throws Exception {
        assertNotNull(productService);

        int milkQty = 10;
        when(productService.getMilkQty()).thenReturn(milkQty);

        mockMvc.perform(get("/store/milk").contentType(MediaType.APPLICATION_JSON_UTF8)    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("OK")) )
                .andExpect(jsonPath("$.result.qty", is(milkQty)) );
    }

    @Test
    public void testGetEggsQty() throws Exception {
        int eggQty = 999;
        when(productService.getEggsQty() ).thenReturn(eggQty);

        mockMvc.perform(get("/store/eggs").contentType(MediaType.APPLICATION_JSON_UTF8)    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("OK")) )
                .andExpect(jsonPath("$.result.qty", is(eggQty)) );
    }

    @Test
    public void testBuyMilkNegativeQty() throws Exception {

        when(productService.buyMilk(0)).thenThrow(PositiveIntegerException.class);

        mockMvc.perform(post("/store/buymilk").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : 0 } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("FAIL")) )
                .andExpect(jsonPath("$.err_code", is(101)) );

    }

    @Test
    public void testBuyMilkInvalidAmt() throws Exception {

        mockMvc.perform(post("/store/buymilk").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : \"abc\" } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("FAIL")) )
                .andExpect(jsonPath("$.err_code", is(100)) );

    }

    @Test
    public void testBuyMilk() throws Exception {
        assertNotNull(productService);

        when(productService.buyMilk(1)).thenReturn(1);

        mockMvc.perform(post("/store/buymilk").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : 1 } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("OK")) )
                .andExpect(jsonPath("$.result.qty", is(1)) );

        when(productService.buyMilk(20)).thenReturn(9);

        mockMvc.perform(post("/store/buymilk").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : 20 } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("OK")) )
                .andExpect(jsonPath("$.result.qty", is(9)) );
    }



    @Test
    public void testBuyEggsNegativeQty() throws Exception {

        when(productService.buyEggs(0)).thenThrow(PositiveIntegerException.class);

        mockMvc.perform(post("/store/buyeggs").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : 0 } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("FAIL")) )
                .andExpect(jsonPath("$.err_code", is(101)) );

    }

    @Test
    public void testBuyEggsInvalidAmt() throws Exception {

        mockMvc.perform(post("/store/buyeggs").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : \"abc\" } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("FAIL")) )
                .andExpect(jsonPath("$.err_code", is(100)) );

    }

    @Test
    public void testBuyEggs() throws Exception {
        assertNotNull(productService);

        when(productService.buyEggs(1)).thenReturn(1);

        mockMvc.perform(post("/store/buyeggs").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : 1 } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("OK")) )
                .andExpect(jsonPath("$.result.qty", is(1)) );

        when(productService.buyEggs(20)).thenReturn(9);

        mockMvc.perform(post("/store/buyeggs").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"qty\" : 20 } ")    )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andExpect(jsonPath("$.status", is("OK")) )
                .andExpect(jsonPath("$.result.qty", is(9)) );
    }

}