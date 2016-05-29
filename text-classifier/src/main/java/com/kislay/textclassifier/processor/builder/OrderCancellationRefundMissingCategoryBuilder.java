package com.kislay.textclassifier.processor.builder;

import com.kislay.textclassifier.model.OrderIdExtractionModel;

/**
 *
 * @author kislay.verma
 */
public class OrderCancellationRefundMissingCategoryBuilder {

    private static Long TICKET_ID = 1l;

    public static String build(String requestText) throws Exception {
        //validate(requestText);
        String orderId = OrderIdExtractionModel.getOrderId(requestText);
        if (orderId == null) {
            return "You request (lacking order id) has been recorded with id OrderCancellationRefundMissingRequest" + TICKET_ID++ + ". Please provide the order id for faster resolution of the issue";
        }

        return "You request has been recorded with id OrderCancellationRefundMissingRequest" + TICKET_ID++;
    }

    private static void validate(String requestText) throws Exception {
        String orderId = OrderIdExtractionModel.getOrderId(requestText);
        if (orderId == null) {
            throw new Exception("Please include the order id in your request");
        }
    }
}
