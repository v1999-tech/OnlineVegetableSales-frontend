pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "ovs:1.0"
    JAR_FILE = "target/ovs.jar"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', 'https://github.com/v1999-tech/OnlineVegetableSales-frontend.git'
      }
    }
    stage('Build') {
      steps {
        echo "Building with Maven..."
        bat 'mvn clean install -DskipTests'
      }
    }
    stage('Test') {
      steps {
        echo "Running tests..."
        bat 'mvn test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
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
        bat "docker build -t ovs:1.0 ."
        echo "stopping old container (if exists)..."
        bat "docker rm -f ovs-container || echo 'No container to remove'"
        echo "Running new container..."
        bat "docker run -d --name ovs-container -p 8080:8080 ovs:1.0"
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
