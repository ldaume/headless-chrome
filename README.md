| Build Status | Dependencies UpToDate | Latest Version | License |
|:------------:|:---------------------:|:--------------:|:-------:|
| [![Build Status](https://ci.reinvent-software.de/buildStatus/icon?job=Headless-Chrome-Build)](https://ci.reinvent-software.de/job/Headless-Chrome-Build) | [![Dependencies UpToDate](https://ci.reinvent-software.de/buildStatus/icon?job=Headless-Chrome-DependencyCheck)](https://ci.reinvent-software.de/job/Headless-Chrome-DependencyCheck) | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/software.reinvent/headless-chrome/badge.svg)](https://maven-badges.herokuapp.com/maven-central/software.reinvent/headless-chrome) | [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) |

Headless Chrome
==========

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Abstract](#abstract)
- [Usage](#usage)
	- [Repo](#repo)
		- [SBT](#sbt)
		- [Maven](#maven)
	- [Guice Binding](#guice-binding)
	- [Config](#config)
		- [Chrome](#chrome)
		- [ChromeDriver](#chromedriver)
	- [Maven](#maven)
		- [Repo](#repo)
		- [Dependency](#dependency)
	- [SBT](#sbt)
		- [Repo](#repo)
		- [Dependency](#dependency)

<!-- /TOC -->

# Abstract

Google Chrome version 59+ contains a real headless mode with no need of any display like xvfb or vnc.

# Usage

## Repo
Just add the following [maven central](https://mvnrepository.com/artifact/software.reinvent/commons) dependency.

### SBT
Add dependency to `build.sbt`.
```bash
libraryDependencies += "software.reinvent" % "headless-chrome" % "0.3.0"
```

### Maven
```xml
<dependency>
    <groupId>software.reinvent</groupId>
    <artifactId>headless-chrome</artifactId>
    <version>0.3.0</version>
</dependency
```


## Guice Binding

If you use [Guice](https://github.com/google/guice) for injections, just bind the [Provider](https://github.com/google/guice/wiki/ProviderBindings) `HeadlessChromeProvider`.

## Config

The config uses the [typesafe config](https://github.com/typesafehub/config).

| Path                    | Description                               |             Default             |      Required      |
|:------------------------|:------------------------------------------|:-------------------------------:|:------------------:|
| webdriver.chrome.driver | The path to the chromedriver binary       |      bundled in resources       |        :x:         |
| webdriver.chrome.binary | The path to the chrome binary             | /usr/bin/google-chrome-unstable | :white_check_mark: |
| chrome.window.size      | The window size as string: "width,height" |           "1920,1200"           |        :x:         |
| webdriver.user.agent    | The user agent used in chrome             |  default HeadlessChrome agent   |        :x:         |

### Chrome

Use Chrome version 60.

For Windows one could use the portable App from [here](https://sites.google.com/a/chromium.org/chromedriver/downloads).

### ChromeDriver

Use the ChromeDriver version 2.31 which is available [here](https://sites.google.com/a/chromium.org/chromedriver/downloads).


## Maven

### Repo

```xml
<project ...>
 <repositories>
    <repository>
      <id>reinvent.software</id>
      <url>https://maven.reinvent-software.de/nexus/content/groups/public/</url>
    </repository>
 </repositories>
</project>
```

### Dependency

```xml
<dependency>
  <groupId>software.reinvent</groupId>
  <artifactId>headless-chrome</artifactId>
  <version>0.2.2</version>
</dependency>
```

## SBT

### Repo
Add maven repo to `build.sbt`.
```scala
resolvers ++= Seq(
  Resolver.mavenLocal,
  "ReInvent Software OSS" at "https://maven.reinvent-software.de/nexus/content/groups/public"
)
```

### Dependency
```scala
libraryDependencies += "software.reinvent" % "headless-chrome" % "0.2.2"
```
