package com.kislay.textclassifier.processor.builder;

import com.kislay.textclassifier.model.OrderIdExtractionModel;
import com.kislay.textclassifier.model.ReturnIdExtractionModel;

/**
 *
 * @author kislay.verma
 */
public class ReturnRefundCategoryBuilder {

    private static Long TICKET_ID = 1l;

    public static String build(String requestText) throws Exception {
        //validate(requestText);
        String orderId = OrderIdExtractionModel.getOrderId(requestText);
        String returnId = ReturnIdExtractionModel.getReturnId(requestText);
        if (orderId == null && returnId == null) {
            return "You request (missing return and order ids) has been recorded with id ReturnRefundRequest" + TICKET_ID++ + ". Please provide the order/return id for faster resolution of the issue";
        }
        return "You request has been recorded with id ReturnRefundRequest" + TICKET_ID++;
    }

    private static void validate(String requestText) throws Exception {
        String orderId = OrderIdExtractionModel.getOrderId(requestText);
        String returnId = ReturnIdExtractionModel.getReturnId(requestText);
        if (orderId == null && returnId == null) {
            throw new Exception("Please include either the order id or the return id in your request");
        }
    }
}
