name			:= "scscene"

organization	:= "de.djini"

version			:= "0.4.0"

scalaVersion	:= "2.9.2"

libraryDependencies	++= Seq(
	"de.djini"	%% "scutil"	% "0.9.0"	% "compile",
	"de.djini"	%% "scgeom"	% "0.4.0"	% "compile"
)

scalacOptions	++= Seq("-deprecation", "-unchecked")
