package com.ly.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Basic {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Basic API 1");

    //Trivial example
    CompletableFuture<String> cFuture1 = new CompletableFuture<>();
    String result1 = cFuture1.get();
    cFuture1.complete("Future Result");
    System.out.println("result = " + result1);

    /**
     * See the one above. get() block until Future complete.
     * Everything above run in a single thread so get will block forever.
     * The program will stuck at line 13
     *
     * With that idea, tell me if below code will work (swap line 13 and 14)
     *
     *     CompletableFuture<String> cFuture1 = new CompletableFuture<>();
     *     cFuture1.complete("Future Result");
     *     String result1 = cFuture1.get();
     *     System.out.println("result = " + result1);
     *
     */

  }
}
