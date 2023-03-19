pipeline {

  agent any 
  
  stages {
    
    stage("build") {
      steps {
        echo 'building the applications...'
        sh 'mvn clean install'
      }
    }
    
    
    stage("test") {
      steps {
        echo 'testing the applications...'
      }
    }
    
    
    stage("deploy") {
      steps {
        echo 'deploying the applications...'
      }
    }
    
  }

}
