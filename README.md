# Selenium

**Package:** 

Java version 11 - jdk-11.0.17.jdk

Maven version 3 - apache-maven-3.9.0

Chrome driver ChromeDriver 111.0.5563.64

Firefox driver geckodriver-v0.32.2

**Command:**

mvn clean install --> install package

cd selenium_grid

java -jar selenium-server-4.8.1.jar hub --> start selenium grid server

java -jar -Dwebdriver.gecko.driver=geckodriver -Dwebdriver.chrome.driver=chromedriver selenium-server-4.8.1.jar node --config node_config.json
--> create node

mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DgridHub=<gridHubURL> --> run test
allure generate --clean --> generate report
  
