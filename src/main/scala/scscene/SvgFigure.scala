package scscene

import java.awt.Graphics2D

import java.io.File
import java.net.URI
import com.kitfox.svg._

import scgeom._

object SvgFigure {
	def load(file:File):SVGDiagram	= load(file.toURI)
	def load(uri:URI):SVGDiagram	= SVGCache.getSVGUniverse getDiagram uri
}

case class SvgFigure(
	clip:Option[Clip], 
	transform:SgAffineTransform,
	svg:SVGDiagram
) extends Figure {
	lazy val globalBounds:SgRectangle = 
			(transform apply (SgRectangle fromRectangle2D svg.getViewRect)).normalize
	
	final def globalPicked(at:SgPoint):Boolean	=
			(clip forall { _ globalPicked at }) &&
			(transform.inverse exists { t => 
				!(svg pick (t apply at.toPoint2D, new java.util.Vector) isEmpty) 
			})
	
	def paintImpl(g:Graphics2D) {
		for (c <- clip) {
			g clip c.globalShape
		}
		g transform transform.toAffineTransform
		svg.render(g)
	}
}
