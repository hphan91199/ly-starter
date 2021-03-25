package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombine {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Topic:" + ThenCombine.class.getName());

    /**
     * To continue function chains without nesting - independent from each other
     * Input: Supplier<T>
     * Output:"CompletableFuture<T>
     */
    CompletableFuture<String> happyScore = getName().thenCombine(getAge(), (name, age) -> {
      if ("Ly".equals(name) && age == 5) {
        return "Very happy";
      }
      return "Not so happy";
    });

    System.out.println("Happy score = " + happyScore.get());
  }


  //Get name
  static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() ->
    {
      return "Ly";
    });
  }

  //Get age
  static CompletableFuture<Integer> getAge() {
    return CompletableFuture.supplyAsync(() -> {
      return Integer.valueOf(5);
    });
  }
}
