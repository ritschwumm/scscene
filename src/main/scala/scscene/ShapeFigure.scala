package scscene

import java.awt.{ Paint, Stroke, Composite, Graphics2D, Shape }

import scgeom._

case class ShapeFigure(
	clip:Option[Clip], 
	transform:SgAffineTransform, 
	composite:Composite, 
	stroke:Option[Stroke],
	outline:Option[Paint], 
	fill:Option[Paint], 
	shape:Shape
) extends Figure {
	// TODO use stroke.getLineWidth - transformed!
	lazy val globalBounds:SgRectangle = 
			SgRectangle fromRectangle2D (stroke match {
				case None			=> globalShape.getBounds2D
				case Some(stroke)	=> ShapeUtil strokedBounds (globalShape, stroke)
			})
	
	final def globalPicked(at:SgPoint):Boolean	= 
			(clip forall { _ globalPicked at }) &&
			(globalShape contains at.toPoint2D)
			
	// TODO private?
	lazy val globalShape:Shape	= transform apply shape
	
	def paintImpl(g:Graphics2D) {
		for (c <- clip) {
			g clip c.globalShape
		}
		g transform transform.toAffineTransform
		g setComposite composite
		
		for (p <- fill) {
			g setPaint p
			g fill shape
		}
		for (s <- stroke; p <- outline) {
			g setStroke s
			g setPaint p
			g draw shape
		}
	}
}
