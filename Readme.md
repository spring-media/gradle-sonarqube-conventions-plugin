Gradle SonarQube Plugin ![Build Status](https://snap-ci.com/WeltN24/gradle-sonarqube-plugin/branch/master/build_image)
=======================

The plugin applies [SonarQube](http://www.sonarqube.org/) configuration to projects according our best practices at [welt](https://github.com/WeltN24).

## Usage

See [plugin portal](https://plugins.gradle.org/plugin/de.weltn24.sonarqube).

### Requires

- Following properties are supported
    - sonar.host.url (1),(2) (default: __none__)
    - sonar.jdbc.url (1) (default: __none__)
    - sonar.jdbc.driverClassName (1) (default: __com.mysql.jdbc.Driver__)
    - sonar.jdbc.username (1) (default: __sonar__)
    - sonar.jdbc.password (1)
    - sonar.login (1), (2) (default: __sonar__)
    - sonar.username (1), (2) (default: __sonar__)
    - sonar.password (1), (2)
    - sonar.github.login (optional) (default: __none__)
    - sonar.github.oauth (optional) (default: __none__)
    - sonar.github.repository (optional) (default: __none__)
    - sonar.github.pullRequest (optional) (default: __none__)
    
--       
Requirements for Tasks:  
    (1) sonarRunner   
    (2) sonarRunnerPreview


## This Plugin adds the following features to your Project:

### gradle plugins:
- java
- sonar-runner
    
### Dependencies:
    
### Tasks:
- sonarRunner
- sonarRunnerPreview

## Publishing

Publishing is automatically done by [SnapCI](https://snap-ci.com/WeltN24/gradle-sonarqube-plugin/branch/master) after a commit with increased version.

## Contributing

Contributions are more than welcome. Please follow the pull request [pro tips](https://guides.github.com/activities/contributing-to-open-source/#contributing) in order to submit your changes.

## License 

Copyright (c) 2015 WeltN24 GmbH

Licensed under the [MIT license](https://tldrlegal.com/license/mit-license).
