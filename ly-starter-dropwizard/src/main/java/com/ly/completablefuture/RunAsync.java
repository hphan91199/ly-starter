package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RunAsync {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Topic:" + RunAsync.class.getName());

    /**
     * To run background task asynchronously and return nothing
     * Input: Runnable object
     * Output:"CompletableFuture<Void>
     *
     *
     * What if I want to return something? Use supplyAsync
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

    future.get();

    /**
     * Exact same thing in lambda
     */
    CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
          // Simulate a job
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            throw new IllegalStateException(e);
          }
          System.out.println("I sleep in a separate thread 2");
          // End of job
        }
    );

    future2.get();
  }

}
