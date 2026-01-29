pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "ovs:1.0"
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
        bat 'mvn clean install -DskipTests'
      }
    }
    stage('Package') {
      steps {
        echo "Packaging..."
        bat 'mvn package -DskipTests'
      }
    }
    stage('Docker Build & Deploy') {
      steps {
        echo "Building Docker image..."
        bat "docker build -t vijetavernekar/ovs:1.0 ."
      }
    }
    stage('Docker Push to Docker Hub') {
      steps {
        withCredentials([usernamePassword(credentialsId:'dockerhub-creds', usernameVariable:'DOCKER_USER', passwordVariable:'DOCKER_PASS')]) {
          bat """
          docker login -u %DOCKER_USER% -p %DOCKER_PASS%
          docker push vijetavernekar/ovs:1.0
          """
        }
      }
    }
    stage('Deploy with Docker Compose') {
      steps {
        bat "docker-compose down || echo 'No container running'"
        bat "docker-compose up -d"
      }
    }
    stage('Deploy to Kubernetes') {
      steps {
        withCredentials([file(credentialsId: 'docker-desktop-kubeconfig', variable: 'KUBECONFIG')]) {
        bat 'kubectl apply -f k8s/'
        }
      }
    }
    stage('Verify Deployment') {
      steps {
        echo "Listing docker container..."
        bat "docker ps -a"
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
