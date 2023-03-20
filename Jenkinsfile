pipeline {

  agent any 
  
  stages {
    
    stage("build") {
      steps {
         configFileProvider([configFile(fileId: 'app-documents-service-api', variable: 'settingsFile')]) {
           echo 'building the applications...'
           
           script {
              def config = readJSON file:"$settingsFile"
              sh "mvn clean package"
           }
        }
        
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
        
           configFileProvider([configFile(fileId: 'app-documents-service-api', variable: 'settingsFile')]) {
           echo 'deploying the applications...'
            script {
              echo "The file $settingsFile"
              def config = readJSON file:"$settingsFile"
              echo "The host for the  branch is: ${config.PORT}"
              echo 'deploying the applications...'
              sh 'docker rm -f  app-tra-documents-service-api || true'
              sh "docker run -e PORT=${config.PORT} -e APP_VERSION=${config.APP_VERSION} -e SQL_URL_CONECTION=${config.SQL_URL_CONECTION} -e SQL_USERNAME=${config.SQL_USERNAME} -e SQL_PASSWORD=${config.SQL_PASSWORD} -tid --name app-tra-documents-service-api -p 8082:80 oiestradag/app-tra-documents-service-api /bin/bash"
            }
        }       
      }
    }
    
    /*stage("k8s deploy") {
      steps {
        echo 'deploying the applications...'
        sh 'kubectl apply -f app-k8s.yaml'
      }
    }*/
    
    
  }

}
