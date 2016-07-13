package com.luxoft.training.solid.store;

public interface Stock {

    ProductData takeProduct(String name, int count) throws ProductNotFoundException, NotEnoughInStockException;
}
