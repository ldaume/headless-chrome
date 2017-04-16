Headless Chrome
==========
<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Abstract](#abstract)
- [Status](#status)
- [Usage](#usage)
	- [Maven](#maven)
		- [Repo](#repo)
		- [Dependency](#dependency)
	- [SBT](#sbt)
		- [Repo](#repo)
		- [Dependency](#dependency)

<!-- /TOC -->

# Abstract

Google Chrome version 59+ contains a real headless mode with no need of any display like xvfb or vnc.

# Status

| Build Status | Dependencies UpToDate | License |
|:------------:|:---------------------:|:-------:|
| [![Build Status](https://ci.reinvent-software.de/buildStatus/icon?job=Headless-Chrome-Build)](https://ci.reinvent-software.de/job/Headless-Chrome-Build) | [![Dependencies UpToDate](https://ci.reinvent-software.de/buildStatus/icon?job=Headless-Chrome-DependencyCheck)](https://ci.reinvent-software.de/job/Headless-Chrome-DependencyCheck) | [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) |

# Usage

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
  <version>0.1.0</version>
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
`libraryDependencies += "software.reinvent" % "headless-chrome" % "0.1.0"`
