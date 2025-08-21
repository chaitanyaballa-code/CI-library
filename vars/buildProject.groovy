def call(String projectType) {
    stage('Build') {
        if (projectType == 'maven') {
            sh 'mvn clean install -DskipTests'
        } else if (projectType == 'npm') {
            sh 'npm install'
            sh 'npm run build || true' // allow if no build script
        } else if (projectType == 'dotnet') {
            sh 'dotnet restore'
            sh 'dotnet build --no-incremental'
        }
    }
}
