package com.ly.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ZAllOf {

  public static void main(String[] args) {
    System.out.println("Topic:" + ZAllOf.class.getName());

    /**
     * allOf : all must complete
     */

    List<String> webLink = Arrays.asList("link1", "link2", "link3");

    List<CompletableFuture<String>> pageContentFuture = webLink.stream().map(link -> download(link))
        .collect(Collectors.toList());

    CompletableFuture<Void> allFuture = CompletableFuture
        .allOf(pageContentFuture.toArray(new CompletableFuture[pageContentFuture.size()]));
    //here

    //Above return void, but we can get output of all completable list like this

    CompletableFuture<List<String>> allPageContentFuture = allFuture.thenApply(v ->
    {
      return pageContentFuture.stream().map(content -> content.join())
          .collect(Collectors.toList());
    });
  }

  //Dummy
  private static CompletableFuture<String> download(String link) {
    return CompletableFuture.supplyAsync(() -> {
      return null;
    });
  }

}
