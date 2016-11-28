node {    
    stage('SCM') {
        git 'https://github.com/zzt93/Ops.git'
    }
    stage('QA') {
        sh "source ~/.env || true && sonar-scanner"
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -e MYSQL_ALLOW_EMPTY_PASSWORD=true -d mysql/mysql-server"
        sh "docker cp rs.sql my:/tmp"
        waitUntil {
            sh "docker exec my sh -c 'mysql < /tmp/rs.sql'"
        }

        sh "docker stop tom || true"
        sh "docker rm tom || true"
        sh "docker run --name tom -p 11111:8080 -d tomcat"
        sh "docker cp target/risk-system.war tom:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
