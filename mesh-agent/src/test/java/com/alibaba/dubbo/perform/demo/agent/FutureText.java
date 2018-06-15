package com.alibaba.dubbo.perform.demo.agent;

import com.alibaba.dubbo.performance.demo.agent.dubbo.model.RpcResponse;

import java.util.concurrent.*;

public class FutureText implements Future<Object> {
    private CountDownLatch latch = new CountDownLatch(1);

    private Object response;

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
        return response;
    }

    public void done(Object response){
        this.response = response;
        latch.countDown();
    }
}