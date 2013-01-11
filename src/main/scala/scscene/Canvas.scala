package scscene

import java.awt.{ Graphics, Graphics2D, Rectangle }
import java.awt.geom.{ Rectangle2D }
import java.awt.event.{ MouseEvent, KeyEvent }
import javax.swing.{ JComponent }

import scutil.ext.BooleanImplicits._

import scgeom._

final class Canvas extends JComponent {
	setOpaque(true)
	         
	private var figures:Seq[Figure]		= Seq.empty
	private var repaints:Seq[Rectangle]	= Seq.empty
	
	def setFigures(newFigures:Seq[Figure]) {
		figures	= newFigures
		// TODO slow
		repaints foreach repaint
		repaints		= figures map { _.globalBounds.toRectangle2D.getBounds }
		repaints foreach repaint
	}
	
	override protected def paintComponent(g1:Graphics) {
		val	g		= g1.asInstanceOf[Graphics2D]
		val	clip	= g.getClipBounds
		
		GraphicsUtil highQuality g
		
		g setPaint this.getBackground	// TODO make reactive?
		g fill clip
		
		val sgClip	= SgRectangle fromRectangle2D clip
		figures filter { _.globalBounds intersects sgClip } foreach { _ paint g }
	}
	
	repaint()
}
