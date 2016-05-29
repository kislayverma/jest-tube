package com.kislay.textclassifier.processor.builder;

/**
 *
 * @author kislay.verma
 */
public class PaymentButNoOrderCategoryBuilder {

    private static Long TICKET_ID = 1L;

    public static String build(String requestText) throws Exception {
        validate();
        return "Your request is created with request id PaymentButNoOrderRequest" + TICKET_ID++;
    }

    private static void validate() {
        
    }
}
