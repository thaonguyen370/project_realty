pipeline {
    agent any
    tools {
        maven 'maven_3'
         java 'jdk_8'
    }
    stages {
         stage('Build') {
            steps {
                echo 'haha'
                sh 'java -version'
                sh 'mvn -version'
                // Add your Maven build steps here
                sh 'mvn clean install -DSkiptests'
            }
        }
    }
}