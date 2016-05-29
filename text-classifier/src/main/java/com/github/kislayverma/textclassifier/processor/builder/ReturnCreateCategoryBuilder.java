package com.github.kislayverma.textclassifier.processor.builder;

import com.github.kislayverma.textclassifier.model.OrderIdExtractionModel;

/**
 *
 * @author kislay.verma
 */
public class ReturnCreateCategoryBuilder {

    private static Long TICKET_ID = 1l;

    public static String build(String requestText) throws Exception {
        validate(requestText);

        return "Your request is created with request id ReturnCreateRequest" + TICKET_ID++;
    }

    private static void validate(String requestText) throws Exception {
//        String orderId = OrderIdExtractionModel.getOrderId(requestText);
//        if (orderId == null) {
//            throw new Exception("Please include the order id in your request");
//        }
    }
}
