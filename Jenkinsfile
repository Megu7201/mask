node{
    stage("Git clone"){
        git url:"http://192.168.11.11:3000/andy/mask.git"
    }
    stage('Sonar Analysis'){
        withSonarQubeEnv('sonar'){
            withMaven(maven:'maven'){
                sh "mvn clean install -D skipTests"
                withCredentials([usernamePassword(credentialsId:'92da05f6-e4ed-4bb0-a317-1df36d74ff66',usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                    sh "mvn sonar:sonar  -D sonar.login=$USERNAME -D sonar.password=$PASSWORD -D sonar.host.url=http:\\192.168.11.11:9000 -D sonar.projectKey=mask"
                }
            }
        }
    }
}

node{
    stage('Quality Gate'){
        timeout(time:1,unit:'MINUTES'){
            def qg= waitForQualityGate()
            if (qg.status != 'OK'){
                error "Pipeline aborted due to quality gate failed!"
            }
        }
    }

    stage('Deploy to testing Enviornment'){
        sh 'scp target/*.war root@172.17.0.7:/usr/local/tomcat/webapps'
    }

    stage('Run ZAP for DAST'){
        ///var/jenkins_home/tools/com.cloudbees.jenkins.plugins.customtools.CustomTool/ZAP/ZAP_2.10.0/zap.sh -daemon -host localhost -port 8082 -config api.key=ZAPROXY-PLUGIN -dir /var/jenkins_home/.ZAP
        // sh 'zap.sh'
     

            sh 'ssh -p 8877  soselab@140.121.197.135 "docker run --rm -v /home/soselab/Desktop/test:/zap/wrk/:rw -t owasp/zap2docker-stable zap-baseline.py  -t http://192.168.11.11:8080/pharmacy/ -r report.html" || true'
        
        
    }
}