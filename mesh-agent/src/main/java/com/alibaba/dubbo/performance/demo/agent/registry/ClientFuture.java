package com.alibaba.dubbo.performance.demo.agent.registry;

import com.alibaba.dubbo.performance.demo.agent.dubbo.model.RpcResponse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ClientFuture implements Future<Object> {
    private CountDownLatch latch = new CountDownLatch(1);

    private String response;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException {
         //boolean b = latch.await(100, TimeUnit.MICROSECONDS);
        latch.await();
        try {
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error";
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException {
        boolean b = latch.await(timeout,unit);
        return response.getBytes();
    }

    public void done(String response){
        this.response = response;
        latch.countDown();
    }
}