pipeline {
    agent {
        kubernetes {
        yaml '''
            apiVersion: v1
            kind: Pod
            metadata:
                labels:
                    jenkin-job: selenium
            spec:
                containers:
                - name: maven
                  image: maven:3.8.6-openjdk-11-slim
                  command:
                  - cat
                  tty: true
                  volumeMounts:
                  - name: m2
                    mountPath: /root/.m2
                - name: allure
                  image: frankescobar/allure-docker-service:2.19.0
                  command:
                  - cat
                  tty: true
                volumes:
                - name: m2
                  emptyDir: {}
            '''
        }
    }

    options {
        cache(name: 'maven-repo', paths: '~/.m2')
    }

    stages {
        stage('Automation Test'){
            steps {
                script {
                    container('maven') {
                        sh 'mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DgridHub=http://moon.agileops.int/'
                    }
                }
            }
        }

        stage('Allure Report'){
            steps {
                script {
                    container('allure') {
                        sh 'allure generate --clean -o allure-report'
                    }
                }
            }
        }
    }

    post {
        always {
            publishHTML (target : [allowMissing: false,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'allure-report',
            reportFiles: 'index.html',
            reportName: 'allure-report',
            reportTitles: '', 
            useWrapperFileDirectly: true])
        }
    }
}