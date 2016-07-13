package com.luxoft.training.solid.store;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {

    public static final double DELIVERY_COST = 12;
    private static int receiptNo;

    private final int id;
    private List<Product> products;
    private boolean hasDelivery;

    public Cart(int id) {
        this.id = id;
        hasDelivery = false;
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addDelivery() {
        this.hasDelivery = true;
    }

    public double getTotalPrice() {
        double productsTotal = 0;
        for (Product p : products) {
            productsTotal += p.getFullPriceForAll();
        }
        double deliveryCost = hasDelivery ? DELIVERY_COST : 0;
        return productsTotal + deliveryCost;
    }

    public String fillReceipt(Receipt receipt) {
        for (Product p : products) {
            p.fillReceipt(receipt);
        }
        if (hasDelivery) {
            receipt.addDelivery(DELIVERY_COST);
        }
        receipt.setTotalPrice(getTotalPrice());
        return receipt.toString();
    }

    public CartData getData() {
        List<ProductData> pd = new ArrayList<>();
        for (Product p : products) {
            pd.add(p.getData());
        }
        return new CartData(id, pd, hasDelivery);
    }
}
