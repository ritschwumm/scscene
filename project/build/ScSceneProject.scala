import sbt._
      
final class ScSceneProject(info:ProjectInfo) extends DefaultProject(info) {
	// dependencies
	val scutil 	= "de.djini"	%% "scutil"		% "0.0.1"	% "compile"
	val	scgeom	= "de.djini"	%% "scgeom"		% "0.0.1"	% "compile"
	
	// issue compiler warnings
	override def compileOptions	= super.compileOptions ++ Seq(Unchecked)
}
