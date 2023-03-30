pipeline {
    // Setup worker to run the pipeline
    agent {
        node { label 'autotest_slave'}
    }
    environment {
        PATH = "/opt/apache-maven-3.9.1/bin:$PATH"
        LANG = 'en_US.UTF-8'
        LANGUAGE = 'en_US.UTF-8'
        LC_ALL = 'en_US.UTF-8'
    }

    stages {
        stage('Build'){
            steps {
                script {
                    sh '''
                        mvn clean test -DsuiteFile='src/test/resources/test-suites/CucumberRunner.xml' -DgridHub='http://localhost:4444'
                        allure generate --clean
                    '''
                }
            }
        }
    }

    post {
        success {
            // Notify slack channel, email, etc.
            echo 'success'
            
            // Publish the report
            publishHTML (target : [allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'allure-report',
                reportFiles: 'index.html',
                reportName: 'allure-report',
                reportTitles: '', 
                useWrapperFileDirectly: true])
        }
        failure {
            // Notify slack channel, email, etc.
            echo 'failure'
        }
    }
}