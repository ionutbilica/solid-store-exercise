package com.luxoft.training.solid.store.persistence.file;

import com.luxoft.training.solid.store.*;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileCartsRepo implements CartsRepo {

    private final IdGenerator idGenerator;
    private final Map<Integer, SerializableCartData> carts;

    private static final File REPO_FILE = new File("carts.repo");

    public FileCartsRepo(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
        carts = new FileBackedMap<>(REPO_FILE);
    }

    @Override
    public int createNewCart() {
        int id = idGenerator.generateId();
        carts.put(id, new SerializableCartData(new CartData(id)));
        return id;
    }

    @Override
    public CartData getCart(int cartId) {
        SerializableCartData cartData = carts.get(cartId);
        if (cartData == null) {
            throw new CartNotFoundException(cartId);
        }
        return cartData.asCartData();
    }

    @Override
    public void saveCart(CartData cartData) {
        carts.put(cartData.getId(), new SerializableCartData(cartData));
    }
}
