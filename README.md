# Selenium
Java version 11 - jdk-11.0.17.jdk
Maven version 3 - apache-maven-3.9.0
Chrome driver version 111
Firefox driver
Command:
mvn clean install --> install package
java -jar selenium-server-4.8.1.jar hub --> start selenium grid server
java -jar -Dwebdriver.gecko.driver=geckodriver -Dwebdriver.chrome.driver=chromedriver selenium-server-4.8.1.jar node --config node_config.json
--> create node
mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DgridHub=<gridHubURL> --> run test
allure generate --clean --> generate report