pipeline {
agent {
    docker {
        image 'kristianfoss/openjdk-maven-node:latest'
        args '-u root'
    }
}
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