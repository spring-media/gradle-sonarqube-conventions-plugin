**DEPRECATED** - use [SonarLint](http://www.sonarlint.org/) for local code analysis!

Gradle SonarQube Conventions Plugin [![Build Status](https://snap-ci.com/WeltN24/gradle-sonarqube-conventions-plugin/branch/master/build_image)](https://snap-ci.com/WeltN24/gradle-sonarqube-conventions-plugin/branch/master)
=======================

The plugin applies [SonarQube](http://www.sonarqube.org/) configuration and tasks to projects according our best practices at [welt](https://github.com/WeltN24).

## Usage

See [plugin portal](https://plugins.gradle.org/plugin/de.weltn24.sonarqube-conventions).

The plugin applies only conventions and best practices. Thus you have to configure the [sonarqube](https://plugins.gradle.org/plugin/org.sonarqube) in your project as well.

Example:

```
buildscript {
    repositories {
        maven { url "https://plugins.gradle.org/m2/" }
    }

    dependencies {
        classpath(
            'org.sonarqube.gradle:gradle-sonarqube-plugin:1.0',
            'gradle.plugin.de.weltn24:sonarqube-conventions:2.0.0'            
        )
    }
}

apply plugin: 'org.sonarqube'
apply plugin: 'de.weltn24.sonarqube-conventions'
``` 

## Preconditions
- plugin tested with Sonar [5.2+](http://docs.sonarqube.org/display/SONAR/Release+5.2+Upgrade+Notes)
- plugin tested with [Gradle](http://gradle.org/) 2.4+ in single and multi project setups

## Conventions

### Tasks

The plugin adds *sonarqubePreview* task to the project. This task can be used in order to execute the SonarQube analysis in *preview* mode - a full analysis without storing it's results in the database (see [documentation](http://www.sonarqube.org/analysis-vs-preview-vs-incremental-preview-in-sonarqube/) for details). 

We use this tasks locally before pushing code changes to Git and on our PR checker pipelines.

### Configuration

| Key                       | Default value                                   | Applied for tasks               | Mandatory |
| ------------------------- | ----------------------------------------------- | ------------------------------- | --------- |
|sonar.projectName          | ${project.rootProject.name}                     | sonarqube, sonarqubePreview | true      |
|sonar.sourceEncoding       | UTF-8                                           | sonarqube, sonarqubePreview | true      |
|sonar.jacoco.itReportPath  | ${project.buildDir}/jacoco/integrationTest.exec | sonarqube                     | true      |
|sonar.host.url             |                                                 | sonarqube, sonarqubePreview | true      |
|sonar.login                | sonar                                           | sonarqube, sonarqubePreview | true      |
|sonar.password\*           |                                                 | sonarqube, sonarqubePreview | true      |
|sonar.github.login         |                                                 | sonarqube                     | false     |
|sonar.github.oauth         |                                                 | sonarqube                     | false     |
|sonar.github.repository    |                                                 | sonarqube                     | false     |
|sonar.github.pullRequest   |                                                 | sonarqube                     | false     |

(\*) the *sonar.password* property is the only property which must be set in order to successfuly apply the plugin at all.

All default values can be overwritten in the target project:

```
sonarqube {
    properties {
        // overriding properties in 'weltn24-sonarqube-conventions' plugin
        property 'sonar.projectName', 'my-fancy-name'
        
        // adding additional properties
        property 'sonar.exclusions', 'src/main/resources/**'
    }
}
```

## Testing

Testing the functionality of the plugin during the development can be achieved manually by executing the following steps:

 1. Publish your new changes to your local maven repository with `./gradlew publishToMavenLocal` (don't forget to increment the version number)
 2. Add the local Maven cache as a repository in an another gradle project than can integrate with the plugin:
 
    ```
    buildscript {
        repositories {
            mavenLocal()
        }
    }    
    ```

 3. Use the updated version of the plugin as a local dependency with the gradle project from the step before and run the plugin.

## Publishing

Publishing is automatically done by [SnapCI](https://snap-ci.com/WeltN24/gradle-sonarqube-conventions-plugin/branch/master) after a commit with increased version.

## Contributing

Contributions are more than welcome. Please follow the pull request [pro tips](https://guides.github.com/activities/contributing-to-open-source/#contributing) in order to submit your changes.

## License 

Copyright (c) 2015 WeltN24 GmbH

Licensed under the [MIT license](https://tldrlegal.com/license/mit-license).
