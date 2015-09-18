package de.weltn24.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class SonarQubePlugin implements Plugin<Project> {

    void apply(Project project) {
        final String reportDir = "${project.rootProject.buildDir}/reports/sonar"

        boolean isPreview = false
        project.gradle.taskGraph.whenReady {taskGraph ->
            if (taskGraph.hasTask(":sonarRunnerPreview")) {
                isPreview = true
                configureSonarRunnerPreview(project, reportDir)
            }
        }

        project.gradle.taskGraph.whenReady {taskGraph ->
            if (taskGraph.hasTask(":sonarRunner")) {
                configureSonarRunner(project, reportDir, isPreview)
            }
        }

        project.apply(plugin: 'sonar-runner')

        project.task('sonarRunnerPreview',
                dependsOn: ['sonarRunner'],
                group: 'verification',
                description: "Analyzes root project '${project.rootProject.name}' and its subprojects with Sonar Runner in preview mode.") {

            doFirst {
                project.file(reportDir).mkdirs()
            }

            doLast {
                println "HTML Issues Report generated, see file://${reportDir}/issues-report.html"
            }
        }

    }

    // set properties for running SonarQube in preview mode
    def configureSonarRunnerPreview(project, reportDir) {
        project.sonarRunner {
            sonarProperties {
                property "sonar.analysis.mode", "preview"
                property "sonar.issuesReport.html.enable", "true"
                property "sonar.issuesReport.html.location", "${reportDir}"
            }
        }
    }

    def configureSonarRunner(project, reportDir, isPreview) {
        final String sonarPassword = project.getProperty('sonar.password')
        final String jdbcPassword = getJdbcPassword(project, isPreview)

        //default values
        final String sonarHostUrl = project.hasProperty('sonar.host.url') ? project.getProperty('sonar.host.url') : ""
        final String sonarJdbcUrl = project.hasProperty('sonar.jdbc.url') ? project.getProperty('sonar.jdbc.url') : ""
        final String jdbcDriverClassName = project.hasProperty('sonar.jdbc.driverClassName') ? project.getProperty('sonar.jdbc.driverClassName') : 'com.mysql.jdbc.Driver'
        final String jdbcUsername = project.hasProperty('sonar.jdbc.username') ? project.getProperty('sonar.jdbc.username') : 'sonar'
        final String sonarUsername = project.hasProperty('sonar.username') ? project.getProperty('sonar.username') : 'sonar'
        final String sonarLogin = project.hasProperty('sonar.login') ? project.getProperty('sonar.login') : 'sonar'

        //github pr-checker plugin support
        final String sonarGithubLogin = project.hasProperty('sonar.github.login') ? project.getProperty('sonar.github.login') : ""
        final String sonarGithubOAuth = project.hasProperty('sonar.github.oauth') ? project.getProperty('sonar.github.oauth') : ""
        final String sonarGithubRepository = project.hasProperty('sonar.github.repository') ? project.getProperty('sonar.github.repository') : ""
        final String sonarGithubPullRequest = project.hasProperty('sonar.github.pullRequest') ? project.getProperty('sonar.github.pullRequest') : ""

        project.sonarRunner {
            toolVersion = '2.4'

            sonarProperties {
                if(!properties['sonar.projectName']){
                    property "sonar.projectName", project.rootProject.name
                }

                property "sonar.host.url", sonarHostUrl

                // JDBC/database properties
                property "sonar.jdbc.url", sonarJdbcUrl
                property "sonar.jdbc.driverClassName", jdbcDriverClassName
                property "sonar.jdbc.username", jdbcUsername
                property "sonar.jdbc.password", jdbcPassword

                // authentication
                property "sonar.login", sonarLogin
                property "sonar.password", sonarPassword
                //deprecated
                property "sonar.username", sonarUsername

                // code coverage
                property "sonar.jacoco.itReportPath", "${project.buildDir}/jacoco/integrationTest.exec"
                property "sonar.sourceEncoding", "UTF-8"

                // github pr-checker
                if(sonarGithubLogin && sonarGithubOAuth && sonarGithubRepository && sonarGithubPullRequest) {
                    property "sonar.github.login", sonarGithubLogin
                    property "sonar.github.oauth", sonarGithubOAuth
                    property "sonar.github.repository", sonarGithubRepository
                    property "sonar.github.pullRequest", sonarGithubPullRequest
                }
            }
        }
    }
    String getJdbcPassword(project, isPreview) {
        if (isPreview) {
            return "not needed for preview"
        }
        return project.getProperty('sonar.jdbc.password')
    }

}
