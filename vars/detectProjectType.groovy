//this function detects if the project is maven, npm or dotnet
def call() {
    if (fileExists('pom.xml')) {
        return 'maven'
    } else if (fileExists('package.json')) {
        return 'npm'
    } else if (!findFiles(glob: '*.csproj').isEmpty() || fileExists('*.sln')) {
        return 'dotnet'
    } else {
        error "❌ Unknown project type. No pom.xml, package.json, or .csproj found."
    }
}