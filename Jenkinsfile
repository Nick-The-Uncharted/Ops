node {
    stage('Env') {
        sh "grep '^export PATH' ~/.bash_profile | sh"
    }
    
    stage('SCM') {
        git 'https://github.com/zzt93/Ops.git'
    }
    stage('QA') {
        sh 'sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --add-host=docker:172.17.0.2 -p 3306 --name my -p 11111:8080 -d tomcat"
        sh "docker cp target/risk-system.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
