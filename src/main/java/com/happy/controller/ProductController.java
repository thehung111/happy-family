package com.happy.controller;


import com.happy.exception.PositiveIntegerException;
import com.happy.model.Product;
import com.happy.model.QtyReq;
import com.happy.model.QtyResult;
import com.happy.model.RestResultWrapper;
import com.happy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/store")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path="/milk", method = RequestMethod.GET)
    public RestResultWrapper getMilkQty(){
        RestResultWrapper result = new RestResultWrapper();
        result.setStatus(RestResultWrapper.ApiStatus.OK);
        result.setResult(new QtyResult(productService.getMilkQty()));
        return result;
    }

    @RequestMapping(path="/eggs", method = RequestMethod.GET)
    public RestResultWrapper getEggsQty(){
        RestResultWrapper result = new RestResultWrapper();
        result.setStatus(RestResultWrapper.ApiStatus.OK);
        result.setResult(new QtyResult(productService.getEggsQty()));
        return result;
    }

    @RequestMapping( value = "/buymilk", method = RequestMethod.POST )
    public RestResultWrapper buyMilk(@RequestBody QtyReq qtyReq){

        RestResultWrapper result = new RestResultWrapper();

        result.setStatus(RestResultWrapper.ApiStatus.OK);
        int purchasedQty = productService.buyMilk(qtyReq.getQty() );
        result.setResult(new QtyResult( purchasedQty ));

        return result;
    }

    @RequestMapping( value = "/buyeggs", method = RequestMethod.POST )
    public RestResultWrapper buyEggs(@RequestBody QtyReq qtyReq){
        RestResultWrapper result = new RestResultWrapper();

        result.setStatus(RestResultWrapper.ApiStatus.OK);
        int purchasedQty = productService.buyEggs(qtyReq.getQty() );
        result.setResult(new QtyResult(purchasedQty));

        return result;
    }

    @ExceptionHandler(PositiveIntegerException.class)
    public RestResultWrapper handlePostiveIntegerException(PositiveIntegerException pe ) {
        RestResultWrapper result = new RestResultWrapper();
        result.setErr(RestResultWrapper.ApiError.POSITIVE_INTEGER_INPUT);
        return result;

    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public RestResultWrapper handleInvalidInputException(org.springframework.http.converter.HttpMessageNotReadableException invalidEx ) {
        RestResultWrapper result = new RestResultWrapper();
        result.setErr(RestResultWrapper.ApiError.INTEGER_INPUT);
        return result;

    }


}
