package com.luxoft.training.solid.store;

import java.util.HashMap;
import java.util.Map;

public class TestStock implements Stock {

    public static final double BREAD_PRICE = 5;
    public static final double WINE_PRICE = 10;
    public static final String BREAD_NAME = "Bread";
    public static final String WINE_NAME = "Wine";

    private final Map<String, Product> products;

    public TestStock() {
        products = new HashMap<>();
        addProduct(BREAD_NAME, BREAD_PRICE, 100);
        addProduct(WINE_NAME, WINE_PRICE, 100);
    }

    @Override
    public ProductData takeProduct(String name, int count) {
        Product productInStock = findProduct(name);
        removeProduct(productInStock, count);
        return new Product(productInStock, count).getData();
    }

    private void addProduct(String name, double price, int count) {
        Product p;
        try {
            p = findProduct(name);
            p = new Product(name, price, p.getCount() + count);
        } catch (ProductNotFoundException e) {
            p = new Product(name, price, count);
        }
        products.put(name, p);
    }

    private Product findProduct(String name) {
        Product productInStock = products.get(name);
        if (productInStock == null) {
            throw new ProductNotFoundException(name);
        }
        return productInStock;
    }

    private void removeProduct(Product p, int countToRemove) {
        if (countToRemove > p.getCount()) {
            throw new NotEnoughInStockException(p.getData(), countToRemove);
        }
        p = new Product(p, p.getCount() - countToRemove);
        products.put(p.getName(), p);
    }
}
