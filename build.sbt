name			:= "scscene"

organization	:= "de.djini"

version			:= "0.0.4"

scalaVersion	:= "2.9.2"

//publishArtifact in (Compile, packageBin)	:= false

publishArtifact in (Compile, packageDoc)	:= false

publishArtifact in (Compile, packageSrc)	:= false

libraryDependencies	++= Seq(
	"de.djini"	%% "scutil"	% "0.0.6"	% "compile",
	"de.djini"	%% "scgeom"	% "0.0.4"	% "compile"
)

scalacOptions	++= Seq("-deprecation", "-unchecked")
