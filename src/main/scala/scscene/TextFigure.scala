package scscene

import java.awt.{ Paint, Font, Composite, Shape, Graphics2D }

import scgeom._

case class TextFigure(
		clip:Option[Clip], 
		transform:SgAffineTransform, composite:Composite, 
		paint:Paint, font:Font, 
		text:String
) extends Figure {
	private val layout	= ShapeUtil textLayout (text, font)
	private val	origin 	= SgPoint fromPoint2D (ShapeUtil textOrigin layout)
	
	lazy val globalBounds:SgRectangle = 
			SgRectangle fromRectangle2D (ShapeUtil inflate (globalShape.getBounds2D, 1))
	
	final def globalPicked(at:SgPoint):Boolean	= 
			(clip forall { _ globalPicked at }) &&
			(globalShape contains at.toPoint2D)
			
	private lazy val globalShape:Shape	= {
		val otrans	= SgAffineTransform translate -origin
		val	shape	= layout getOutline otrans.delegate
		transform apply shape
	}
	
	def paintImpl(g:Graphics2D) {
		for (c <- clip) {
			g clip c.globalShape
		}
		g transform transform.toAffineTransform
		g setComposite composite
		
		g setPaint paint
		g setFont font
		g drawString(
				text,
				-origin.x.toFloat,
				-origin.y.toFloat)
	}
}
