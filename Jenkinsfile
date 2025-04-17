pipeline {
  agent any

  environment {
    // Jenkins credential IDs & image name
    DOCKERHUB_CREDENTIALS   = '7ba68df7-f8e7-4c24-bf72-416e5ea5849b'
    IMAGE_NAME              = 'yukta1327/studentsurvey'
    KUBE_CONFIG_CREDENTIALS = 'kubeconfig'
    K8S_NAMESPACE           = 'default'
  }

  tools {
    maven 'Maven'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }

    stage('Build & Push Docker Image') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: "${DOCKERHUB_CREDENTIALS}",
          usernameVariable: 'DOCKER_USER',
          passwordVariable: 'DOCKER_PASS'
        )]) {
          sh 'echo "$DOCKER_PASS" | docker login --username "$DOCKER_USER" --password-stdin'
          sh "docker build -t ${IMAGE_NAME}:latest ."
          sh "docker push ${IMAGE_NAME}:latest"
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        withCredentials([file(
          credentialsId: "${KUBE_CONFIG_CREDENTIALS}",
          variable: 'KUBECONFIG'
        )]) {
          sh """
            kubectl set image \
              deployment/studentsurvey-deployment \
              studentsurvey=${IMAGE_NAME}:latest \
              --namespace ${K8S_NAMESPACE}
          """
        }
      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
