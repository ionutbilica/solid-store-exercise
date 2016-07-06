package com.luxoft.training.solid.store.receipt;

import com.luxoft.training.solid.store.receipt.IdGenerator;

public class MockIdGenerator implements IdGenerator {

    private final int fixedId;

    public MockIdGenerator(int fixedId) {
        this.fixedId = fixedId;
    }

    @Override
    public int generateId() {
        return fixedId;
    }
}
