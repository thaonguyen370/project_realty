pipeline {
    agent none
    stages {
         stage('Build') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                // Add your Maven build steps here
                sh 'mvn clean install -DSkiptests'
            }
        }
    }
}