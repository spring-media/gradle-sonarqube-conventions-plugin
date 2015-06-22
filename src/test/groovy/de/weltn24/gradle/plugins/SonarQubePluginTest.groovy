package de.weltn24.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.assertTrue


class SonarQubePluginTest {

    static Project project

    @BeforeClass
    public static void setUp(){
        project = ProjectBuilder.builder().build()

        project.apply plugin: 'weltn24-sonarqube'
    }

    @Test
    public void sonarQubePluginAddsSonarQubeTasksToProject() {
        println 'sonarQubePluginAddsSonarQubeTasksToProject'
        assertTrue(project.tasks.findByName('sonarRunnerPreview') instanceof Task)
    }
}
