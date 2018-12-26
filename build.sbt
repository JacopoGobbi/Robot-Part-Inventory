name := "RobotPartInventory"
 
version := "0.2-SNAPSHOT" 
      
lazy val `robotPartInventory` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  evolutions,
  jdbc,
  ehcache,
  ws,
  guice,
  "com.h2database" % "h2" % "1.4.197",
  "io.getquill" %% "quill-jdbc" % "2.5.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % "test",
  "org.mockito" % "mockito-core" % "2.22.0" % Test
)

      