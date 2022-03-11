package automationpractice.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src\\test\\resources\\features\\Ecommerce.feature",
		glue="StepDef",
		tags="@ecommerce",
		plugin= {"pretty",
				"html:\\target\\html\\htmlreport.htm", 
				"json:\\target\\json\\file.json",

		},
		
		dryRun=false,
		monochrome = true,
		publish = true
		

		)



public class TestRunner {

}
