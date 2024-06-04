package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public static void setUp(Scenario scenario){
        System.out.println("STARTING SCENARIO: " + scenario.getName());
    }


    @After
    public static void cleanUp(Scenario scenario){
        System.out.println("ENDING SCNEARIO: " + scenario.getName());
    }






}
