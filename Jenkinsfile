pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }

  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
    disableConcurrentBuilds()
  }

  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK8_Centos' //Preinstalada en la Configuración del Master
    gradle 'Gradle6.0.1_Centos' //Preinstalada en la Configuración del Master
  }

  //Aquí comienzan los “items” del Pipeline
  stages {
    stage('Checkout') {
      steps {
        echo "------------>Checkout<------------"
        checkout([
        $class: 'GitSCM', branches: [[name: '*/refactoring']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[
        credentialsId: 'GitHub_danielparra05', url: 'https://github.com/DanielParra05/BACKEND_ADN.git']]])
      }
    }

    stage('Compile & Unit Tests') {
      steps {
        echo "------------>Unit Tests<------------"
        sh 'gradle --b ./microservicio/build.gradle clean compileJava'
        sh 'gradle --b ./microservicio/build.gradle test'
      }
    }

    stage('Static Code Analysis') {
      steps {
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
          sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
        }
      }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
        //Construir sin tarea test que se ejecutó previamente
        sh 'gradle --b ./microservicio/build.gradle build -x test'
      }
    }
  }
  post {
    always {
      echo 'This will always run'
    }
    success {
      //This will run only if successful
      junit 'microservicio/dominio/build/test-results/test/*.xml'
    }
    failure {
      //This will run only if failed
      mail(to: 'daniel.parra@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}