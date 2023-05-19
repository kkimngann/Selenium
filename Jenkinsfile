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
                - name: minio-cli
                  image: minio/mc
                  command:
                  - cat
                  tty: true
                  volumeMounts:
                  - name: shared-data
                    mountPath: /data
                - name: jq
                  image: stedolan/jq:latest
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
        stage('restore cache') {
            steps {
                script {
                    container('minio-cli') {
                        sh "mc alias set minio http://minio.minio.svc.cluster.local:9000 vJlIj3mKR4Df9ZHt 9qZLIDh5A14IciJfEcmwGAk9iVQxHt4L"
                        sh "mc mirror minio/selenium/.m2 /data &> /dev/null"
                    }
                }
            }
        }

        stage('automated test'){
            steps {
                script {
                    container('maven') {
                        sh '''
                        mkdir -p .m2 && cp -rT /data ~/.m2 &> /dev/null
                        mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DgridHub=http://moon.agileops.int/ > result.txt || true
                        cp -rT ~/.m2 /data &> /dev/null
                        '''
                    }
                    // Get summary test result
                    result = sh returnStdout: true, script: 'cat result.txt | sed -n \'/Failed tests/,/Tests run/p\''

                    container('minio-cli') {
                        sh "mc mirror /data minio/selenium/.m2 --overwrite &> /dev/null"
                    }
                }
            }
        }

        stage('publish report'){
            steps {
                script {
                    def blocks = [
                        [
                            "type": "section",
                            "text": [
                                "type": "mrkdwn",
                                "text": "*FAILED TEST*"
                            ]
                        ],
                        [
                            "type": "divider"
                        ],
                        [
                            "type": "section",
                            "text": [
                                "type": "mrkdwn",
                                "text": "Job *${env.JOB_NAME}* has been failed.\n*Summary:*\n${result}"
                            ]
                        ],
                        [
                            "type": "divider"
                        ],
                        [
                            "type": "section",
                            "text": [
                                "type": "mrkdwn",
                                "text": "More info at:\n *Build URL:* ${env.BUILD_URL}\n *Allure Report:* ${env.BUILD_URL}allure-report"
                            ]
                        ],
                    ]

                    container('allure') {
                        sh 'allure generate --clean'
                    }

                    dir('allure-results') {
                        container('jq') { 
                            sh 'jq -s \'.[] | select(.status != "passed") | .uuid\' *-result.json > failedTest.txt'
                        }
                        
                        def file = readFile('failedTest.txt')
                        if (file.trim().length() > 0) {
                            slackSend channel: 'automation-test-notifications', blocks: blocks, teamDomain: 'agileops', tokenCredentialId: 'jenkins-slack', botUser: true
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'allure-results/**/*'

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
