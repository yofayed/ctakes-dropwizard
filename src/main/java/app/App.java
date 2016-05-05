package app;


import app.resources.AppResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import clinicalpipeline.ClinicalPipeline;

public class App extends Application<AppConfiguration> {
    
    public static void main(String[] args) throws Exception {
        beforeStart();
        new App().run(args);
    }

    @Override
    public String getName() {
        return "claims-annotator";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AppConfiguration configuration,
                    Environment environment) {

        AppResource resource = new AppResource(configuration.getDefaultFunction());
        environment.jersey().register(resource);
    }

    public static void beforeStart(){
        ClinicalPipeline.getInstance().getAnalysisEngine();
    }
}