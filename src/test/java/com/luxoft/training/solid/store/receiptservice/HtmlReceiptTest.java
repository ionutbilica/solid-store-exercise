package com.luxoft.training.solid.store.receiptservice;

import com.luxoft.training.solid.store.*;
import com.luxoft.training.solid.store.accounting.MockAccounting;
import com.luxoft.training.solid.store.discount.NoDiscountsRepo;
import com.luxoft.training.solid.store.idgen.MockIdGenerator;
import com.luxoft.training.solid.store.persistence.InMemCartsRepo;
import com.luxoft.training.solid.store.persistence.TestStock;
import com.luxoft.training.solid.store.receipt.ReceiptFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlReceiptTest {

    private Store store;
    private int cartId;

    @Before
    public void beforeTest() throws ParseException {
        Date fixedDate = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("10-12-2015 12:33:44");
        MockClock mockClock = new MockClock(fixedDate);
        MockIdGenerator receiptNoGenerator = new MockIdGenerator(33);
        ReceiptFactory receiptFactory = new ConcreteReceiptFactory(receiptNoGenerator, mockClock);
        store = new Store(new TestStock(), new NoDiscountsRepo(), new InMemCartsRepo(new MockIdGenerator(1)), receiptFactory, new MockAccounting());

        cartId = store.createNewCart();
    }

    @Test
    public void testEmptyCart() {
        String receipt = store.pay(cartId, "CASH", ReceiptFactory.Format.HTML.toString());
        Assert.assertEquals("Empty receipt not as expected."
                , "Our StoreReceipt no.: 33\n" +
                "<div><b>Total</b>: 0.0</div>\n" +
                "<div><b>Date</b>: 10-12-2015 12:33:44</div>\n"
                , receipt);
    }

    @Test
    public void testTwoProducts() {
        store.addProductToCart(TestStock.BREAD_NAME, cartId);
        store.addProductToCart(TestStock.WINE_NAME, 2, cartId);
        String receipt = store.pay(cartId, "CASH", ReceiptFactory.Format.HTML.toString());
        Assert.assertEquals("Receipt for two products not as expected."
        , "Our StoreReceipt no.: 33\n" +
                        "<div><b>Bread</b>: 1 x 5.0 = 5.0</div>\n" +
                        "<div><b>Wine</b>: 2 x 10.0 = 20.0</div>\n" +
                        "<div><b>Total</b>: 25.0</div>\n" +
                        "<div><b>Date</b>: 10-12-2015 12:33:44</div>\n"
                ,receipt
        );
    }

    @Test
    public void testWithDelivery() {
        store.addProductToCart(TestStock.BREAD_NAME, cartId);
        store.addProductToCart(TestStock.WINE_NAME, 2, cartId);
        store.addDeliveryToCart(cartId);
        String receipt = store.pay(cartId, "CASH", ReceiptFactory.Format.HTML.toString());
        Assert.assertEquals("Receipt for two products not as expected."
                , "Our StoreReceipt no.: 33\n" +
                        "<div><b>Bread</b>: 1 x 5.0 = 5.0</div>\n" +
                        "<div><b>Wine</b>: 2 x 10.0 = 20.0</div>\n" +
                        "<div><b>Delivery</b>: 12.0</div>\n" +
                        "<div><b>Total</b>: 37.0</div>\n" +
                        "<div><b>Date</b>: 10-12-2015 12:33:44</div>\n"
                ,receipt
        );
    }
}
