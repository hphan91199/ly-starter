package com.ly.phonebook;

import com.google.common.cache.CacheBuilderSpec;
import com.ly.phonebook.resources.ClientResource;
import com.ly.phonebook.resources.ContactResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is where the app start
 * How to run?
 * Go to the location and run below
 * java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yaml
 */
public class App extends Application<PhonebookConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b) {
        b.addBundle(new ViewBundle());
        b.addBundle(new AssetsBundle());
    }

    @Override
    public void run(PhonebookConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");

        //Create a DB instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");

        final Client client = new JerseyClientBuilder(e).build("REST Client");
        client.addFilter(new HTTPBasicAuthFilter("ly", "ly")); // I can add a filter here for authentication - why someone wanna do this

        //With Cache
        CachingAuthenticator<BasicCredentials, Boolean> authenticator
                = new CachingAuthenticator<BasicCredentials, Boolean>(e.metrics(), new PhonebookAuthentication((jdbi)), CacheBuilderSpec.parse("maximumSize=10000, expireAfterAccess=10m"));
        e.jersey().register(new BasicAuthProvider<Boolean>(authenticator, "Web Service Realm"));
        //

        e.jersey().register(new ClientResource(client));
        e.jersey().register(new BasicAuthProvider<Boolean>(new PhonebookAuthentication(jdbi), "Web Service Realm"));
        e.jersey().register(new ContactResource(jdbi, e.getValidator()));
    }
}
