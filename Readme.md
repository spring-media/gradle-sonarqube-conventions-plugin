Gradle SonarQube Plugin ![Build Status](https://snap-ci.com/WeltN24/gradle-sonarqube-plugin/branch/master/build_image)
=======================

The plugin applies [SonarQube](http://www.sonarqube.org/) configuration to projects according our best practices at [welt](https://github.com/WeltN24).

## Usage

See [plugin portal](https://plugins.gradle.org/plugin/de.weltn24.sonarqube).

## Conventions

### Plugins

The following standard Gradle plugins will be applied automatically:

+ [sonar-runner](https://docs.gradle.org/current/userguide/sonar_runner_plugin.html)

### Tasks

The plugin adds the follwoing tasks to the project:

* sonarRunner (standard tasks from [SonarRunner plugin](https://docs.gradle.org/current/userguide/sonar_runner_plugin.html))
* sonarRunnerPreview

The custom *sonarRunnerPreview* task can be used in order to execute the SonarQube analysis in *preview* mode - a full analysis without stroring it's results in the database (see [documentation](http://www.sonarqube.org/analysis-vs-preview-vs-incremental-preview-in-sonarqube/) for details). 

We use this tasks locally before pushing code changes to Git and on our PR checker pipelines.

### Configuration

| Key | Default value | Applied for tasks| Mandatory |
| ---- | ---- | ------------- | ---------- |
|sonar.projectName| ${project.rootProject.name} | sonarRunner,  sonarRunnerPreview | true |
|sonar.sourceEncoding| UTF-8 | sonarRunner,  sonarRunnerPreview | true |
|sonar.jacoco.itReportPath| ${project.buildDir}/jacoco/integrationTest.exec | sonarRunner | true |
|sonar.host.url|  | sonarRunner,  sonarRunnerPreview | true |
|sonar.jdbc.url|  | sonarRunner | true |
|sonar.jdbc.driverClassName|  com.mysql.jdbc.Driver | sonarRunner | true |
|sonar.jdbc.username|  sonar | sonarRunner | true |
|sonar.jdbc.password|   | sonarRunner | true |
|sonar.login | sonar | sonarRunner, sonarRunnerPreview | true |
|sonar.password\* |  | sonarRunner, sonarRunnerPreview | true |
|sonar.github.login |  | sonarRunner | false |
|sonar.github.oauth |  | sonarRunner | false |
|sonar.github.repository |  | sonarRunner | false |
|sonar.github.pullRequest |  | sonarRunner | false |

(\*) the *sonar.password* property is the only property which must be set in order to successfuly apply the plugin at all.

All default values can be overwritten in the target project:

```
sonarRunner {
    sonarProperties {
        // overriding properties in 'weltn24-sonarqube' plugin
        property 'sonar.projectName', 'my-fancy-name'
        
        // adding additional properties
        property 'sonar.exclusions', 'src/main/resources/**'
    }
}
```

## Publishing

Publishing is automatically done by [SnapCI](https://snap-ci.com/WeltN24/gradle-sonarqube-plugin/branch/master) after a commit with increased version.

## Contributing

Contributions are more than welcome. Please follow the pull request [pro tips](https://guides.github.com/activities/contributing-to-open-source/#contributing) in order to submit your changes.

## License 

Copyright (c) 2015 WeltN24 GmbH

Licensed under the [MIT license](https://tldrlegal.com/license/mit-license).
