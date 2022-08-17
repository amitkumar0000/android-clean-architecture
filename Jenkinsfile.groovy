class Constants {
    static final String COVERAGE = 'Coverage'
    static final String BUILD_STATUS_CREDENTIALS = '265fd975-6eba-47db-af63-b53915199263'
    static final String BUILD_STATE_SUCCESS = 'SUCCESSFUL'
    static final String BUILD_STATE_IN_PROGRESS = 'INPROGRESS'
    static final String BUILD_STATE_FAILED = 'FAILED'
    static final String QUALITY_GATE_OK = "OK"
    static final Integer DEFAULT_TIMEOUT = 3
    static final String DEFAULT_TIMEOUT_UNIT = 'MINUTES'
}

def getBuildType() {
    return params.BUILD_TYPE
}

pipeline {
    agent any

    tools {
        jdk '1.8.0_301-b09'
    }

    stages {
        stage('Assemble') {
            steps {
                echo "Building AAR files-------"
                // Finish building and packaging the AAR
                script {
                    sh "./gradlew clean assemble${getBuildType()}"

                }
            }
        }
    }
}