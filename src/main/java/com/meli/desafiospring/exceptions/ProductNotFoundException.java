package com.meli.desafiospring.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String msg){
        super(msg);
    }
}
