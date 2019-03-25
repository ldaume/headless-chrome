import sbt.Keys._
import sbt._

resolvers ++= Seq(
  Resolver.mavenLocal
)

// Enables publishing to maven repo
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

enablePlugins(SignedAetherPlugin)

disablePlugins(AetherPlugin)

publishTo := {
  val nexus = "https://maven.reinvent-software.de/nexus/"
  if (version.value.trim.endsWith("SNAPSHOT")) {
    Some(Opts.resolver.sonatypeSnapshots)
  } else {
    Some(Opts.resolver.sonatypeReleases)
  }
}

overridePublishBothSettings

overridePublishSignedBothSettings

//credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials.sonatype")

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

homepage := Some(new URL("https://github.com/ldaume/headless-chrome"))

startYear := Some(2017)

licenses := Seq(("MIT", new URL("https://github.com/ldaume/headless-chrome/blob/master/LICENSE")))

/*pomExtra <<= (pomExtra, name, description) { (pom, name, desc) =>
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
}*/



