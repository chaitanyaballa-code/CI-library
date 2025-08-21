def call(String projectType) {
    stage('Test') {
        if (projectType == 'maven') {
            sh 'mvn test'
            junit 'target/surefire-reports/*.xml'
        } else if (projectType == 'npm') {
            sh 'npm test || true'
            junit 'test-results.xml' // adjust to your test reporter
        } else if (projectType == 'dotnet') {
            sh 'dotnet test --logger:trx'
            junit '**/TestResults/*.trx'
        }
    }
}
