package com.kislay.textclassifier.processor;

import com.kislay.textclassifier.processor.builder.BaseCategoryBuilder;
import com.kislay.textclassifier.processor.builder.OrderCancellationCashbackMIssingCategoryBuilder;
import com.kislay.textclassifier.processor.builder.OrderCancellationCouponMissingCategoryBuilder;
import com.kislay.textclassifier.processor.builder.OrderCancellationRefundMissingCategoryBuilder;
import com.kislay.textclassifier.processor.builder.OrderDelayedCategoryBuilder;
import com.kislay.textclassifier.processor.builder.OrderStatusCategoryBuilder;
import com.kislay.textclassifier.processor.builder.PaymentButNoOrderCategoryBuilder;
import com.kislay.textclassifier.processor.builder.PaymentFailedCategoryBuilder;
import com.kislay.textclassifier.processor.builder.ReturnCreateCategoryBuilder;
import com.kislay.textclassifier.processor.builder.ReturnRefundCategoryBuilder;


/**
 *
 * @author kislay.verma
 */
public class CategoryBuilder {

    public static String build(String categoryName, String requestText) throws Exception {
        switch (categoryName) {
            case "payment-failed":
                return PaymentFailedCategoryBuilder.build(requestText);
            case "payment-but-no-order":
                return PaymentButNoOrderCategoryBuilder.build(requestText);
            case "order-status":
                return OrderStatusCategoryBuilder.build(requestText);
            case "order-delayed":
                return OrderDelayedCategoryBuilder.build(requestText);
            case "order-cancellation-cashback-missing":
                return OrderCancellationCashbackMIssingCategoryBuilder.build(requestText);
            case "order-cancellation-no-refund":
                return OrderCancellationRefundMissingCategoryBuilder.build(requestText);
            case "order-cancellation-coupon-missing":
                return OrderCancellationCouponMissingCategoryBuilder.build(requestText);
            case "return-refund":
                return ReturnRefundCategoryBuilder.build(requestText);
            case "return-create":
                return ReturnCreateCategoryBuilder.build(requestText);
            case "insufficient":
                return "Enter more details to create a request";
            default:
                return BaseCategoryBuilder.build(requestText);
        }
    }
}
