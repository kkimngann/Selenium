def result = ''

pipeline {
    agent {
        kubernetes {
        yaml '''
            apiVersion: v1
            kind: Pod
            metadata:
              labels:
                jenkin-job: selenium-browserstack
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
        stage('automated test'){
            steps {
                script {
                    browserstack('binhpham_browserstack') {
                        container('maven') {
                            sh 'mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DBROWSERSTACK_USERNAME=${BROWSERSTACK_USERNAME} -DBROWSERSTACK_ACCESSKEY=${BROWSERSTACK_ACCESS_KEY} > result.txt || true'
                        }
                    }
                    result = sh (script: 'grep "Tests run" result.txt | tail -1', returnStdout: true).trim()
                }
            }
        }

        stage('publish report'){
            steps {
                script {
                    container('allure') {
                        sh 'allure generate --clean'
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

            script {
                // Get hashed build ID after test finished
                def buildID = readFile 'build_hashed_id.txt'
                // Define Slack message blocks
                def blocks = [
                    [
                        "type": "header",
                        "text": [
                            "type": "plain_text",
                            "text": "FINISHED TEST",
                        ]
                    ],
                    [
                        "type": "divider"
                    ],
                    [
                        "type": "section",
                        "text": [
                            "type": "mrkdwn",
                            "text": ":sunny: Job *${env.JOB_NAME}*'s result is ${currentBuild.currentResult}.\n*Summary:*"
                        ]
                    ],
                    [
                    "type": "section",
                    "text": [
                        "type": "mrkdwn",
                        "text": "```${result}```"
                        ]
                    ],
                    [
                        "type": "divider"
                    ],
                    [
                        "type": "section",
                        "text": [
                            "type": "mrkdwn",
                            "text": ":pushpin: More info at:\n• *Build URL:* ${env.BUILD_URL}console\n• *Allure Report:* ${env.BUILD_URL}allure-report\n• *BrowserStack build URL:* https://automate.browserstack.com/dashboard/v2/builds/${buildID}"
                        ]
                    ],
                ]

                // Send notification
                slackSend channel: 'automation-test-notifications', blocks: blocks, teamDomain: 'agileops', tokenCredentialId: 'jenkins-slack', botUser: true
            }
        }
    }
}
