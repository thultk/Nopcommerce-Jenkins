pipeline {
  agent any
  stages {
    stage('Build source') {
      steps {
        bat 'mvn clean'
      }
    }

    stage('Run chrome') {
      parallel {
        stage('Run chrome') {
          steps {
            bat 'mvn test -DBROWSER=chrome'
          }
        }

        stage('Run firefox') {
          steps {
            bat 'mvn test -DBROWSER=firefox'
          }
        }

      }
    }

  }
}