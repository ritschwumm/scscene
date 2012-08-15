name			:= "scscene"

organization	:= "de.djini"

version			:= "0.5.0"

scalaVersion	:= "2.9.2"

libraryDependencies	++= Seq(
	"de.djini"	%% "scutil"	% "0.10.0"	% "compile",
	"de.djini"	%% "scgeom"	% "0.4.0"	% "compile"
)

scalacOptions	++= Seq("-deprecation", "-unchecked")
