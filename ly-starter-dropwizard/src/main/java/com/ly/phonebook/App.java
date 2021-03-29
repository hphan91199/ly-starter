package com.ly.phonebook;

import com.ly.phonebook.resources.ContactResource;
import com.ly.phonebook.resources.SnapshotResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java -jar target/ly-starter-dropwizard-1.0-SNAPSHOT.jar server
 */
public class App extends Application<Configuration> {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void initialize(Bootstrap<Configuration> b) {
  }

  @Override
  public void run(Configuration c, Environment e) throws Exception {
    LOGGER.info("Method App#run() called");
    System.out.println("Hello world, by Dropwizard!");

    e.jersey().register(new ContactResource());
    e.jersey().register(new SnapshotResource());
  }
}
