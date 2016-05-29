package com.github.kislayverma.revproxy.dto;

import org.springframework.web.context.request.async.DeferredResult;

/**
 *
 * @author kislay.verma
 */
public class DeferredGreetingDTO {
    private final String name;
    private DeferredResult<String> deferredResult;

    public DeferredGreetingDTO(DeferredResult<String> deferredResult, String name) {
        this.name = name;
        this.deferredResult = deferredResult;
    }

    public void doStuff() throws InterruptedException {
        Thread.sleep(1000l);
        System.out.println("Running in thread " + Thread.currentThread().getName());
        this.deferredResult.setResult(name);
    }
}
