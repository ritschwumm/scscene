package scscene

import java.awt.{ Paint, Stroke, Composite, Graphics2D, Shape }
import java.awt.geom.{ Point2D, Rectangle2D, AffineTransform }

import scgeom._

case class Clip(transform:SgAffineTransform, shape:Shape) {
	lazy val globalShape:Shape				= transform transformShape shape
	def globalPicked(at:SgPoint):Boolean	= globalShape contains at.toPoint2D
}
