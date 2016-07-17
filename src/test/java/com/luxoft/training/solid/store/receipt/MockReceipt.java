package com.luxoft.training.solid.store.receipt;

import com.luxoft.training.solid.store.receipt.Receipt;

public class MockReceipt implements Receipt {
    @Override
    public void addProduct(String name, int count, double price, double priceForAll) {
    }

    @Override
    public void addDelivery(double cost) {
    }

    @Override
    public void setTotalPrice(double totalPrice) {
    }
}
