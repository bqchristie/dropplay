package com.ia.helloworld;

import com.ia.helloworld.dao.VisitDAO;
import com.ia.helloworld.health.TemplateHealthCheck;
import com.ia.helloworld.resources.VisitResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/public", "/*","index.html"));
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final VisitResource resource = new VisitResource(
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck();

        DBIFactory factory  = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final VisitDAO dao = jdbi.onDemand(VisitDAO.class);


        environment.healthChecks().register("template", healthCheck);
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(resource);
    }

}