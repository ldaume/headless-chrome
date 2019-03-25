
// Project name (artifact name in Maven)
name := """headless-chrome"""

// orgnization name (e.g., the package name of the project)
organization := "software.reinvent"

// version := "0.4.0-SNAPSHOT"
version := "0.3.6"

version in ThisBuild := s"${version.value}"

scalaVersion := "2.12.8"

// project description
description := "Implementation of the headless chrome with chromedriver and selenium."

testOptions += Tests.Argument(jupiterTestFramework, "-q", "-v")

dependencyUpdatesFailBuild := true