pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "ovs:${BUILD_NUMBER}"
    JAR_FILE = "target/ovs.jar"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', url: 'https://github.com/v1999-tech/OnlineVegetableSales-frontend.git'
      }
    }
    stage('Build') {
      steps {
        echo "Building with Maven..."
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Package') {
      steps {
        echo "Packaging..."
        sh 'mvn package -DskipTests'
      }
    }
    stage('Docker Build & Deploy') {
      steps {
        echo "Building Docker image..."
        sh "docker build -t vijetavernekar/ovs:${BUILD_NUMBER} ."
      }
    }

    stage('Trivy Scan') {
            steps {
                sh '''
                trivy image --exit-code 1 --timeout=10m --severity HIGH,CRITICAL vijetavernekar/ovs:${BUILD_NUMBER}
                '''
            }
        }
    stage('Docker Push to Docker Hub') {
      steps {
        withCredentials([usernamePassword(credentialsId:'dockerhub-creds', usernameVariable:'DOCKER_USER', passwordVariable:'DOCKER_PASS')]) {
          sh """
          docker login -u $DOCKER_USER -p $DOCKER_PASS
          docker push vijetavernekar/ovs:${BUILD_NUMBER}
          """
        }
      }
    }
    stage('Deploy with Docker Compose') {
      steps {
        sh "docker-compose down --remove-orphans || true'"
        sh "docker-compose up -d --build"
        sh "docker-compose ps"
      }
    }


    stage('Deploy') {
    steps {
        withCredentials([usernamePassword(
            credentialsId: 'dockerhub-creds',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS')]) {

            sh """
            helm upgrade --install ovs ./helm/ovs-chart \
            --set image.repository=\$DOCKER_USER/ovs \
            --set image.tag=${BUILD_NUMBER}
            """
        }
    }
}
/*
    stage('Deploy to Kubernetes') { 
      steps { withCredentials([file(credentialsId: 'docker-desktop-kubeconfig', variable: 'KUBECONFIG')]) { 
        sh '''
        export KUBECONFIG=$KUBECONFIG
        kubectl apply -f k8s/
        kubectl get pods
        ''' 
      } 
            } 
    }
*/
    stage('Verify Deployment') {
      steps {
        echo "Listing docker container..."
        sh "docker ps -a"
      }
    }
  }
  post {
    success {
      echo "Pipeline completed successfully!"
    }
    failure {
      echo "Pipeline failed. Check logs."
    }
  }
}


  
