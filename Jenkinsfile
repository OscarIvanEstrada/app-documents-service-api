pipeline {

  agent any 
  
  stages {
    
    stage("build") {
      steps {
        echo 'building the applications...'
        sh 'mvn clean package'
      }
    }
    
     stage("build-docker-image") {
      steps {
        echo 'building the docker image...'
        sh 'docker build -t oiestradag/app-tra-documents-service-api .'
      }
    }
    
    
    stage("push-dockerhub") {
      scripts {
        script {
          withCredentials([string(credentialsId: 'docker_hub_oiestradag', variable: 'oiestradag')]){
              sh 'docker login -u oiestradag -p ${oiestradag }'
          }
        }
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
