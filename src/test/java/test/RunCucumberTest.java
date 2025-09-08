package test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {
                "pretty",                          // Красивый консольный вывод
                "html:build/cucumber-report.html", // HTML отчет
                "json:build/cucumber-report.json", // JSON отчет
                "junit:build/cucumber-report.xml"  // JUnit отчет
        },
        monochrome = true
)
public class RunCucumberTest {
}