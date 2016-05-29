package com.kislay.textclassifier.controller;

import com.kislay.textclassifier.model.CategoryDetectionModel;
import com.kislay.textclassifier.processor.CategoryBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ticket/")
public class TicketController {
    private final Log logger = LogFactory.getLog(getClass());

    @ResponseBody
    @RequestMapping(value = "/create/{userName}", method = RequestMethod.POST, consumes = "text/plain")
    public String create(@PathVariable String userName, @RequestBody String requestText) throws Exception {
        logger.info("In the create method with username " + userName + " and request text " + requestText);
        String category = CategoryDetectionModel.getCategory(requestText);
        logger.info("Category is " + category);
        try {
            return CategoryBuilder.build(category, requestText);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
