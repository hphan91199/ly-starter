package com.ly.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ZAnyOf {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("Topic:" + ZAllOf.class.getName());

    /**
     * anyOf : must have 1  complete
     */

    List<String> webLink = Arrays.asList("link1", "link2", "link3");

    List<CompletableFuture<String>> pageContentFuture = webLink.stream().map(link -> download(link))
        .collect(Collectors.toList());

    CompletableFuture<Object> anyFuture = CompletableFuture
        .anyOf(pageContentFuture.toArray(new CompletableFuture[pageContentFuture.size()]));
    //here

    System.out.println(anyFuture.get());

  }

  //Dummy
  private static CompletableFuture<String> download(String link) {
    return CompletableFuture.supplyAsync(() -> {
      return null;
    });
  }

}
