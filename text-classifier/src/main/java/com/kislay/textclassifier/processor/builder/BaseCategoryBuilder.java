package com.kislay.textclassifier.processor.builder;

/**
 *
 * @author kislay.verma
 */
public class BaseCategoryBuilder {

    private static Long TICKET_ID = 1l;

    public static String build(String requestText) throws Exception {
        validate();
        return "Request" + TICKET_ID++;
    }

    private static void validate() {
        
    }
}
