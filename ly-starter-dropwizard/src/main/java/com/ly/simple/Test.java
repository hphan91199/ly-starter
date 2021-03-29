package com.ly.simple;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ly.protobuf.generated.DogProto;

public class Test {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    System.out.println("Hello");

    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(com.google.protobuf.util.JsonFormat.printer().print(
        DogProto.Dog.newBuilder().setAge(1).setFullName("Prince").build()));
    sb.append(",");
    sb.append(com.google.protobuf.util.JsonFormat.printer()
        .print(DogProto.Dog.newBuilder().setAge(1).setFullName("Pudding").build()));
    sb.append("]");

    System.out.println(sb.toString());
  }
}
