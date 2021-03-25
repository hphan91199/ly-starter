package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorNThreadPool {

  /**
   * Whenever synAsync/supplyAsync called, method execute their tasks in separate thread If you
   * don't supply an executor/thread pool, by default thread obtained from global
   * FookJoinPool.commonPool(); You have the option supply your own thread pool
   */
  public static void main(String[] args) {

    /**
     * Here I'm not supplying a thread pool or executor
     */
    CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {

      @Override
      public void run() {
        // Simulate a job
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          throw new IllegalStateException(e);
        }
        System.out.println("I sleep in a separate thread 1");
        // End of job
      }
    });

    /**
     * Here I create a thread pool and pass it to the method
     */
    Executor executor = Executors.newFixedThreadPool(10);
    CompletableFuture<Void> future2 = CompletableFuture.runAsync(new Runnable() {

      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          throw new IllegalStateException(e);
        }
        System.out.println("I sleep in a separate thread 1");
      }
    }, executor); //See here?

  }

}
