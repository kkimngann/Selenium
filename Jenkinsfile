def result = ''
def buildID = ''

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
                  image: frankescobar/allure-docker-service:2.21.0
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
                    browserstack('ngannguyen-browserstack') {
                        container('maven') {
                            sh '''
                            mkdir -p .m2 && cp -rT /data ~/.m2 &> /dev/null
                            mvn clean test -DsuiteFile=src/test/resources/test-suites/CucumberRunner.xml -DBROWSERSTACK_USERNAME=${BROWSERSTACK_USERNAME} -DBROWSERSTACK_ACCESSKEY=${BROWSERSTACK_ACCESS_KEY} > result.txt || true
                            cp -rT ~/.m2 /data &> /dev/null
                            '''
                        }
                    }

                    // result = sh returnStdout: true, script: 'cat result.txt | sed -n \'/Failed tests/,/Tests run/p\''
                    sh 'cat result.txt'
                    buildID = sh returnStdout: true, script: 'cat build_hashed_id.txt'
                    
                    container('minio-cli') {
                        sh "mc mirror /data minio/selenium/.m2 --overwrite &> /dev/null"
                    }
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
            reportFiles: '**/*.html',
            reportName: 'allure-report',
            reportTitles: '', 
            useWrapperFileDirectly: true])

            script {
                // Send result notification to Slack
                def jobName = build job: 'selenium-browserstack', parameters: [[$class: 'StringParameterValue', name:'HOST',value: HOST]], propagate: false, wait:true
                def jobResult = jobName.getResult()
                
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
                            "text": ":sunny: Job *${env.JOB_NAME}*'s result is ${jobResult}}.\n*Summary:*"
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
