Gradle SonarQube Plugin
========================================

![Build Status](https://snap-ci.com/WeltN24/gradle-sonarqube-plugin/branch/master/build_image)

## Usage

Build script snippet for use in all Gradle versions:

    buildscript {
      repositories {
        maven {
          url "https://plugins.gradle.org/m2/"
        }
      }
      dependencies {
        classpath "gradle.plugin.de.weltn24:sonarqube:1.1.0"
      }
    }
    
    apply plugin: "de.weltn24.sonarqube"
    
Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:

    plugins {
      id "de.weltn24.sonarqube" version "1.0.13"
    }


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

Publishing is automatically done by snap-ci after a commit with increased version.
    
##Copyright (c) 2015 WeltN24 GmbH

Released under the [MIT license](https://tldrlegal.com/license/mit-license).
