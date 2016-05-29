package com.kislay.textclassifier.processor.builder;

import com.kislay.textclassifier.model.OrderIdExtractionModel;
import java.util.Random;

/**
 *
 * @author kislay.verma
 */
public class OrderStatusCategoryBuilder {

    private static Long TICKET_ID = 1l;
    private static final Random rand = new Random();
    private static final String[] statusArr = new String[] {
        "Your order is being processed and will be fulfilled shortly",
        "Your order has been moved to our warehouse",
        "Your order is packed and will be shipped soon through our logistics partners",
        "Your order has been shipped from our warehouse",
        "Your order is out for delivery"
    };

    public static String build(String requestText) throws Exception {
        validate(requestText);
        return statusArr[rand.nextInt(5)];
    }

    private static void validate(String requestText) throws Exception {
        String orderId = OrderIdExtractionModel.getOrderId(requestText);
        if (orderId == null) {
            throw new Exception("Please include the order id in your request");
        }
    }
}
