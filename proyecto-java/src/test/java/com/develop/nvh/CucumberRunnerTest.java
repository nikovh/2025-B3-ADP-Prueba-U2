package com.develop.nvh;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Ruta a la carpeta donde están los archivos .feature
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.develop.nvh.steps")
@ConfigurationParameter(
  key = PLUGIN_PROPERTY_NAME,
  value = "pretty, summary, html:target/cucumber-report.html, json:target/cucumber.json"
)
public class CucumberRunnerTest { }