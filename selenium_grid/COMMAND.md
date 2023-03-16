
// start selenium grid
java -jar selenium-server-4.8.1.jar hub
java -jar -Dwebdriver.<type>.<name>s path/to/selenium/server.jar node --config /path/to/nodeConfig.json
java -jar -Dwebdriver.gecko.driver=geckodriver -Dwebdriver.chrome.driver=chromedriver selenium-server-4.8.1.jar node --config node_config.json
