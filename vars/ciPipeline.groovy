def call(Map config = [:]) {
    /*
     * Wrapper pipeline that runs the full CI flow:
     * - Checkout
     * - Detect project type
     * - Build & Test
     * - Sonar Analysis (optional)
     * - Archive Artifacts
     *
     * Config parameters:
     *   repoUrl   (required) : Git repository URL
     *   branch    (optional) : Branch to checkout (default = "main")
     *   sonar     (optional) : true/false, default true
     *   archive   (optional) : true/false, default true
     */

    node {
        // checkout
        checkoutCode(config.repoUrl, config.get('branch', 'main'))

        // detect
        def type = detectProjectType()
        echo "Detected project type: ${type}"

        // build & test
        buildProject(type)
        testProject(type)

        // sonar (if enabled)
        if (config.get('sonar', true)) {
            sonarAnalysis(type)
        }

        // archive (if enabled)
        if (config.get('archive', true)) {
            archiveArtifactsStage(type)
        }
    }
}
