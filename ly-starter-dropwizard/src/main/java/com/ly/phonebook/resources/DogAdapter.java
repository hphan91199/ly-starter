package com.ly.phonebook.resources;

import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.protobuf.util.JsonFormat;
import com.ly.protobuf.generated.DogProto;
import java.io.IOException;

/**
 * The PersonAdapter class which will provide a {@TypeAdapter} to convert Person proto to a well
 * defined, concise Json and vice-versa.
 */

/**
 * Override the write method and set the json value of the Person message.
 */
public class DogAdapter extends TypeAdapter<DogProto.Dog> {

  @Override
  public void write(JsonWriter jsonWriter, DogProto.Dog dog) throws IOException {
// Call the printer of the JsonFormat class to convert the Person proto message to Json
    jsonWriter.value(JsonFormat.printer().print(dog));
  }

  /**
   * Override the read method to return a {@Dog} object from it's json representation.
   */
  @Override
  public DogProto.Dog read(JsonReader jsonReader) throws IOException {
    // Create a builder for the Person message
    DogProto.Dog.Builder dogBuilder = DogProto.Dog.newBuilder();
    // Use the JsonFormat class to parse the json string into the builder object
    // The Json string will be parsed fromm the JsonReader object
    JsonParser jsonParser = new JsonParser();
    JsonFormat.parser().merge(jsonParser.parse(jsonReader).toString(), dogBuilder);
    // Return the built Person message
    return dogBuilder.build();
  }

}
