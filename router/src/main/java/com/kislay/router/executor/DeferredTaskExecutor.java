package com.kislay.router.executor;

import com.kislay.router.dto.DeferredGreetingDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *
 * @author kislay.verma
 */
public class DeferredTaskExecutor {

    private Queue<DeferredGreetingDTO> taskList = new ConcurrentLinkedQueue<>();
    private List<Thread> consumerThreadList = new ArrayList<>();
    private static final Log logger = LogFactory.getLog(DeferredTaskExecutor.class);
    private static DeferredTaskExecutor INSTANCE;

    private DeferredTaskExecutor() {
        for (int i = 0; i < 50; i++) {
            Thread x = new Thread(new ExecutorThread(), "DR-Thread-" + i);
            x.start();
            consumerThreadList.add(x);
            logger.info("Started thread " + x.getName());
        }
    }

    public static DeferredTaskExecutor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeferredTaskExecutor();
        }

        return INSTANCE;
    }

    public void submitTask(DeferredResult<String> deferredResult, String name) {
        taskList.offer(new DeferredGreetingDTO(deferredResult, name));
    }

    private class ExecutorThread implements Runnable {
        private void consume() throws InterruptedException {
            while (true) {
                DeferredGreetingDTO task = taskList.poll();
                if (task != null) {
                    task.doStuff();
                }
            }
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException ex) {
                //Logger.getLogger(IdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
