package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ZException {

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
        .exceptionally(ex -> {
          System.out.println("We have an exception " + ex);
          //throw new Exception("I can't do this!!!");
          return "Something wrong happened";
        });

    System.out.println("Age check : " + future.get());
  }

}
