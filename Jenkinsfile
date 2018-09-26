#!groovy
node {

    // Get the Maven tool.
    // ** NOTE: This 'maven' Maven tool must be configured
    // **       in the global configuration.
    def mvnHome = tool 'maven'
    def image
    def imageName = 'registry.cn-hangzhou.aliyuncs.com/mengx8/springboot-02-jar'
    def containerId
    def dockerRegistry = 'https://registry.cn-hangzhou.aliyuncs.com'
    def dockerRegistryCredentials = 'docker-registry-aliyun'


    stage('Git pull') { // for display purposes

        // Get some code from a repository
        checkout scm

    }
    stage('Maven build') {
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
    }
    stage('Docker build') {
//        docker.withServer(dockerServer) {
//            image = docker.build(imageName)
//        }
        containerId = sh(returnStdout: true, script: "docker ps --format '{{.ID}}' --filter ancestor=${imageName}").trim()
        image = docker.build(imageName)
        // junit '**/target/surefire-reports/TEST-*.xml'
        // archive 'target/*.jar'
    }
    stage('Docker push') {
        // first: docker registry
        // second: jenkins credentials id
        docker.withRegistry(dockerRegistry, dockerRegistryCredentials) {
            image.push()
        }
    }
    stage('Docker run') {
        if (containerId) {
            echo "container ${containerId} already running, now will stop it"
            sh "docker stop ${containerId}"
            sh "docker rm ${containerId}"
        }

        image.run('-p 8080:8080')
    }

}