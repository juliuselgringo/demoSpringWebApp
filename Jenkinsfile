pipeline{
    agent any
    environment{
        registry = "juliuselgringo/webappspring"
        registryCredential = 'juliuselgringo'
        dockerImage = ''
    }
    tools{
        maven 'Maven'
        jdk 'JDK21'
    }
    stages{
        stage('Git Checkout'){
            steps{
                script{
                    git branch: 'main',
                        credentialsId: 'jenkins',
                        url: 'https://github.com/juliuselgringo/demoSpringWebApp.git'
                }
            }
        }
        stage('Clean workspace'){
            steps{
                cleanWs()
            }
        }
        stage('Build Maven'){
            steps{
                bat 'mvn clean package'
            }
        }
        stage('Build Docker Image'){
            steps{
                script{
                    docker.build('juliuselgringo/webappspring', '-f Dockerfile .')
                }
            }
        }
        stage('Push to Docker Hub'){
            docker.withRegistry('', registryCredential){
                docker.image('juliuselgringo/webappspring').push()
            }
        }
        stage('Deploy docker-compose'){
            steps{
                script{
                    bat 'docker-compose up -d --build --force-recreate --remove-orphans'
                }
            }
        }
    }
}