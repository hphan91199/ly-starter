package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplyAsync {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Topic:" + SupplyAsync.class.getName());

    /**
     * To run background task asynchronously and return something
     * Input: Supplier<T>
     * Output:"CompletableFuture<T>
     */

    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
      @Override
      public String get() {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          throw new IllegalStateException(e);
        }
        return "Return a string of asynchronous computation 1";
      }
    });

    String result1 = future1.get();
    System.out.println(result1);

    /**
     * Exact same thing in lambda
     */
    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() ->
    {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      return "Return a string of asynchronous computation 2";
    });

    String result2 = future2.get();
    System.out.println(result2);
  }

}
