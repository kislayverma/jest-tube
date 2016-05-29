package com.github.kislayverma.textclassifier.processor;

import com.github.kislayverma.textclassifier.processor.builder.BaseCategoryBuilder;
import com.github.kislayverma.textclassifier.processor.builder.OrderDelayedCategoryBuilder;
import com.github.kislayverma.textclassifier.processor.builder.OrderStatusCategoryBuilder;
import com.github.kislayverma.textclassifier.processor.builder.ReturnCreateCategoryBuilder;

/**
 *
 * @author kislay.verma
 */
public class CategoryBuilder {

    public static String build(String categoryName, String requestText) throws Exception {
        switch (categoryName) {
            case "order-status":
                return OrderStatusCategoryBuilder.build(requestText);
            case "order-delayed":
                return OrderDelayedCategoryBuilder.build(requestText);
            case "return-create":
                return ReturnCreateCategoryBuilder.build(requestText);
            case "insufficient":
                return "Enter more details to create a request";
            default:
                return BaseCategoryBuilder.build(requestText);
        }
    }
}
