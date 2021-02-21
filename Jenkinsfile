pipeline {
    agent any

    tools {
        maven "M3"
        jdk "OpenJDK-11"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('Deploy') {
             steps {
                sh "mvn clean heroku:deploy"
             }
        }

    }
}
