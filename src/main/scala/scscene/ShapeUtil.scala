package scscene

import java.awt.{ Stroke, BasicStroke, Font, Shape }
import java.awt.geom.{ Point2D, Rectangle2D, RoundRectangle2D }
import java.awt.font.{ TextLayout, FontRenderContext }

object ShapeUtil {
	def textOrigin(textLayout:TextLayout):Point2D =
			new Point2D.Double(0, textLayout.getBaseline)
	
	def textLayout(text:String, font:Font):TextLayout = 
			// HACK: "" has no size at all, whereas " " has
			new TextLayout(
					if (text == "") " " else text, 
					font, 
					new FontRenderContext(null, false, false))
					
	def strokedBounds(shape:Shape, stroke:Stroke):Rectangle2D = stroke match {
		case bStroke:BasicStroke	=>
			// For some reason (antialiasing?) the bounds returned by
			// BasicStroke is off by one.  This code works around it.
			// if all we want is the bounds, then we don't need to actually
			// stroke the shape.  We've had reports that this is no longer
			// necessary with JDK1.3.
			inflate(shape.getBounds2D, bStroke.getLineWidth.toInt + 2)
		case oStroke =>
			// For some reason (antialiasing?) the bounds returned by
			// BasicStroke is off by one.  This code works around it.
			// We've had reports that this is no longer
			// necessary with JDK1.3.
			inflate((oStroke createStrokedShape shape).getBounds2D, 1)
	}
	
	def inflate(rect:Rectangle2D, value:Double):Rectangle2D =
			new Rectangle2D.Double(
					rect.getX() - value, 
					rect.getY() - value,
					rect.getWidth() + value*2,
					rect.getHeight() + value*2)
					
	def rounded(rect:Rectangle2D, radius:Double):RoundRectangle2D	= new RoundRectangle2D.Double(
			rect.getX, rect.getY,
			rect.getWidth,
			rect.getHeight,
			radius, radius) 
}
