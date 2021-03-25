package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ZHandle {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    /**
     * If an exception handled once it won't progagated further in callback chain
     */

    Integer age = -1;

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      if (age < 0) {
        throw new IllegalStateException("Age cannot be negative");
      }
      return "Age okay";
    })
        .handle((res, ex) -> {
          if (ex != null) {
            System.out.println("Exception is not null! We have an exception");
          }
          return "I have an exception in the chain";
        });

    System.out.println("Age check : " + future.get());
  }

}
