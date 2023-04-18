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
                  - name: shared-data
                    mountPath: /data
                - name: allure
                  image: frankescobar/allure-docker-service:2.19.0
                  command:
                  - cat
                  tty: true
                  volumeMounts:
                  - name: shared-data
                    mountPath: /data
                volumes:
                - name: shared-data
                  emptyDir: {}
            '''
        }
    }

    stages {
        stage('automated test'){
            steps {
                script {
                    container('maven') {
                        sh 'cp -r /data/.m2 ~/ || true'
                        sh 'ls -al ~/.m2'
                        // sh 'mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DgridHub=http://moon.agileops.int/'
                        sh 'cp -r ~/.m2 /data'
                    }
                }
            }
        }

        stage('publish report'){
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