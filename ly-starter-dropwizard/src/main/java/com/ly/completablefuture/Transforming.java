package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Transforming {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Topic:" + Transforming.class.getName());

    /**
     * Process and transform result of completable future when it arrives
     *
     * thenApply()  - HAS return  - HAS access to result of Completable it attached to
     * thenAccept() - NO return   - HAS access to result of Completable it attached to
     * thenRun()    - NO return   - NO access to result of Completable it attached to
     *
     * thenAccept()/thenRun() ususally is the last callback in a chain
     */

    //
    // Example then Apply
    //
    CompletableFuture<String> helloText = CompletableFuture //notice String
        .supplyAsync(() ->
        {
          return "Ly";
        })
        .thenApply(name -> {
          System.out.println("Adding hello");   //notice this print in a different thread
          return "Hello " + name;
        })
        .thenApply(text -> {
          System.out.println("Adding !");
          return text + "!";
        });
    System.out.println(helloText.get());       //notice this print in main thread
    System.out.println("--------------");

    //
    // Example thenAccept
    //
    CompletableFuture<Void> sayName = CompletableFuture //notice void
        .supplyAsync(() ->
        {
          return "Ly";
        }).thenAccept(name -> {
          System.out.println(name);
        });
    System.out.println("--------------");

    //
    // Example thenRun
    //
    CompletableFuture<Void> sayHi = CompletableFuture //notice void
        .supplyAsync(() ->
        {
          return "whatever";
        }).thenRun(() ->
        {
          System.out.println("Hi stranger");
        });
    System.out.println("--------------");

  }

}
