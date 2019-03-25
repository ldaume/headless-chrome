import sbt._

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.bintrayIvyRepo("rtimush", "sbt-plugin-snapshots")
resolvers += Resolver.bintrayIvyRepo("lolhens", "sbt-plugins")
resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.jcenterRepo
sbtPlugin := true

addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.21")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.4.0")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.2")

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.7.0")