// this will archive the artifacts
def call(String projectType) {
    stage('Archive Artifacts') {
        if (projectType == 'maven') {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        } else if (projectType == 'npm') {
            archiveArtifacts artifacts: 'dist/**/*', fingerprint: true
        } else if (projectType == 'dotnet') {
            archiveArtifacts artifacts: '**/bin/**/*.dll', fingerprint: true
        }
    }
}
