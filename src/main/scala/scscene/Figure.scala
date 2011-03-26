package scscene

import java.awt.Graphics2D

import scgeom._

trait Figure {
	def globalBounds:SgRectangle
	def globalPicked(at:SgPoint):Boolean
	
	final def paint(gg:Graphics2D) {
		val	g	 = gg.create().asInstanceOf[Graphics2D]
		
		try {
			paintImpl(g)
		}
		finally {
			g.dispose()
		}
	}
	
	protected def paintImpl(g:Graphics2D)
}
