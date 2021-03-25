package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCompose {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Topic:" + ThenCompose.class.getName());

    /**
     * To continue function chains without nesting - dependent
     * Input: Supplier<T>
     * Output:"CompletableFuture<T>
     */
    CompletableFuture<Integer> result2 = getName(0).thenCompose(name -> getAge(name));
    System.out.println("Nice and neat -> " + result2.get());

    /**
     * What if I use thenApply?
     * Do one task once the other task is done - nested - nah
     */
    CompletableFuture<CompletableFuture<Integer>> result = getName(0)
        .thenApply(name -> getAge(name));
    System.out.println("Messy very messy -> " + result.get().get());
    System.out.println();
  }


  //Get name
  static CompletableFuture<String> getName(int id) {
    return CompletableFuture.supplyAsync(() ->
    {
      return "Ly";
    });
  }

  //Get age
  static CompletableFuture<Integer> getAge(String name) {
    return CompletableFuture.supplyAsync(() -> {
      return name.equals("Ly") ? Integer.valueOf(10) : Integer.valueOf(22);
    });
  }
}
