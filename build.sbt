import sbt.Keys.version

// Project name (artifact name in Maven)
name := """headless-chrome"""

// orgnization name (e.g., the package name of the project)
organization := "software.reinvent"

//version := "0.4.0-SNAPSHOT"
//version in ThisBuild := "0.4.0-SNAPSHOT"
version := "0.3.1"
version in ThisBuild := "0.3.1"

scalaVersion := "2.12.3"

// project description
description := "Implementation of the new headless chrome with chromedriver and selenium."

resolvers ++= Seq(
  Resolver.mavenLocal
)

libraryDependencies ++= Seq(

  // Commons
  "software.reinvent" % "commons" % "0.3.5",

  // chrome
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % "3.4.0",
  "com.assertthat" % "selenium-shutterbug" % "0.6.1" exclude("org.seleniumhq.selenium", "selenium-java"),

  // TEST
  "org.assertj" % "assertj-core" % "3.8.0" % "test",
  "org.assertj" % "assertj-guava" % "3.1.0" % "test" exclude("com.google.guava", "guava"),
  "com.novocode" % "junit-interface" % "0.11" % "test->default",
  "org.jukito" % "jukito" % "1.5" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

dependencyUpdatesFailBuild := true

// Enables publishing to maven repo
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

enablePlugins(SignedAetherPlugin)

disablePlugins(AetherPlugin)

publishTo := {
  val nexus = "https://maven.reinvent-software.de/nexus/"
  if (version.value.trim.endsWith("SNAPSHOT")) {
    Some("snapshots" at nexus + "content/repositories/snapshots")
  } else {
    Some(Opts.resolver.sonatypeStaging)
  }
}

overridePublishBothSettings

overridePublishSignedBothSettings

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
//credentials += Credentials(Path.userHome / ".ivy2" / ".credentials.sonatype")

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

homepage := Some(new URL("https://github.com/ldaume/headless-chrome"))

startYear := Some(2017)

licenses := Seq(("MIT", new URL("https://github.com/ldaume/headless-chrome/blob/master/LICENSE")))

pomExtra <<= (pomExtra, name, description) { (pom, name, desc) =>
  pom ++ xml.Group(
    <scm>
      <url>http://github.com/ldaume/headless-chrome/tree/master</url>
      <connection>scm:git:git://github.com:ldaume/headless-chrome.git</connection>
      <developerConnection>scm:git:git@github.com:ldaume/headless-chrome.git</developerConnection>
    </scm>
      <developers>
        <developer>
          <id>ldaume</id>
          <name>Leonard Daume</name>
          <url>https://reinvent.software</url>
        </developer>
      </developers>
  )
}



