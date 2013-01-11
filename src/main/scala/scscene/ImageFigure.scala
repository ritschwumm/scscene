package scscene

import java.awt.{ Composite, Shape, Graphics2D }
import java.awt.image.{ BufferedImage }

import scgeom._

case class ImageFigure(
	clip:Option[Clip], 
	transform:SgAffineTransform, 
	composite:Composite,
	image:BufferedImage 
) extends Figure {
	// TODO check null ImageObserver
	// TODO check -1 returns
	def globalBounds:SgRectangle = {
			val	rect	= SgRectangle(
					SgSpan(0, image getWidth	null),
					SgSpan(0, image getHeight	null))
			SgRectangle fromRectangle2D (transform transformShape rect.toRectangle2D).getBounds2D inset SgRectangleInsets.one.inverse 
	}
	
	def globalPicked(at:SgPoint):Boolean	= 
			(clip forall { _ globalPicked at }) && 
			(transform.inverse match {
				case Some(t)	=> 
					 val pos	= t apply at
					 val pix	= image getRGB (pos.x.toInt, pos.y.toInt)
					 (pix & 0xff000000) != 0 
				case None		=> 
					false
			})
			
	/*
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
	*/
	
	def paintImpl(g:Graphics2D) {
		for (c <- clip) {
			g clip c.globalShape
		}
		//g transform transform.toAffineTransform
		g setComposite composite
		
		// TODO check null ImageObserver
		g drawImage(
				image,
				transform.toAffineTransform,
				null)
	}
}
