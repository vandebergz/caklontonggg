pipeline {
  agent any
  stages {
    stage('Init') {
      agent any
      steps {
        node(label: 'agent') {
          echo 'Testing pipeline'
        }

      }
    }
  }
}