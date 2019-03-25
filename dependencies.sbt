

libraryDependencies ++= Seq(

  // Commons
  "software.reinvent" % "commons" % "0.3.12",
  "com.google.guava" % "guava" % "27.1-jre",

  // chrome
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % "3.141.59" exclude("com.google.guava", "guava"),
  "com.assertthat" % "selenium-shutterbug" % "0.9" exclude("org.seleniumhq.selenium", "selenium-java"),

  // TEST
  "org.assertj" % "assertj-core" % "3.12.2" % Test,
  "org.assertj" % "assertj-guava" % "3.2.1" % Test exclude("com.google.guava", "guava"),
  "com.novocode" % "junit-interface" % "0.11" % "test->default",
//  "org.jukito" % "jukito" % "1.5" % Test,
  "name.falgout.jeffrey.testing.junit5" % "guice-extension" % "1.1.1" % Test,

  // mocking
  "org.powermock" % "powermock-module-junit4-common" % "2.0.0" % Test,
  "org.powermock" % "powermock-api-mockito2" % "2.0.0" % Test,
  "org.mockito" % "mockito-core" % "2.25.1" % Test
)

dependencyUpdatesFailBuild := true
