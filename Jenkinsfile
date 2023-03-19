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
      steps {
          withCredentials([string(credentialsId: 'docker_hub_oiestradag_pass', variable: 'pass')]){
              sh 'docker login -u oiestradag -p "$pass"'
              sh 'docker push oiestradag/app-tra-documents-service-api'
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
        sh 'docker rm -f  app-tra-documents-service-api || true'
        sh 'docker run -e PORT=8081 -e APP_VERSION=develop -e SQL_URL_CONECTION=jdbc:h2:mem:testdb -e SQL_USERNAME=sa -e SQL_PASSWORD=password -tid --name app-tra-documents-service-api -p 8082:8081 oiestradag/app-tra-documents-service-api /bin/bash'
      }
    }
    
  }

}
