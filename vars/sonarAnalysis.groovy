//this function runs sonar analysis
def call(String projectType) {
    stage('SonarQube Analysis') {
        withSonarQubeEnv('SonarQubeServer') {
            if (projectType == 'maven') {
                sh 'mvn sonar:sonar'
            } else if (projectType == 'npm') {
                sh 'npx sonar-scanner'
            } else if (projectType == 'dotnet') {
                sh 'dotnet sonarscanner begin /k:"project-key" /d:sonar.login=$SONAR_TOKEN'
                sh 'dotnet build'
                sh 'dotnet sonarscanner end /d:sonar.login=$SONAR_TOKEN'
            }
        }
    }
}
