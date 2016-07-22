package com.luxoft.training.solid.store.discount;

import com.luxoft.training.solid.store.discount.Discount;
import com.luxoft.training.solid.store.discount.DiscountsRepo;

import java.util.Collections;
import java.util.Map;

public class NoDiscountsRepo extends DiscountsRepo {
    public NoDiscountsRepo() {
        super(Collections.<String, Discount>emptyMap());
    }
}
