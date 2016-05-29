package com.github.kislayverma.revproxy.controller;

import com.github.kislayverma.revproxy.executor.DeferredTaskExecutor;
import java.util.concurrent.ExecutionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping("/greeting/")
public class DeferredResultController {
    private final Log logger = LogFactory.getLog(getClass());

    @ResponseBody
    @RequestMapping("/blockingGreeting/{name}")
    public String blockingGreeting(@PathVariable String name) throws InterruptedException {
        Thread.sleep(500l);
        logger.info("In the blocking greeting method");
        return name;
    }

    @ResponseBody
    @RequestMapping("/nonBlockingGreeting/{name}")
    public DeferredResult<String> nonBlockingGreeting(@PathVariable String name) throws InterruptedException, ExecutionException {
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        DeferredTaskExecutor.getInstance().submitTask(deferredResult, name);
        logger.info("Submitted non blocking greeting task");

        return deferredResult;
    }
}
