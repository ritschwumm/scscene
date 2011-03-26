package scscene

import java.awt.{ Graphics, Graphics2D  }
import java.awt.geom.{ Rectangle2D }
import java.awt.event.{ MouseEvent, KeyEvent }
import javax.swing.{ JComponent }

import scutil.ext.BooleanImplicits._

import scgeom._

final class Canvas extends JComponent {
	setOpaque(true)
	         
	private var figures:Seq[Figure]	= Nil
	
	def setFigures(newFigures:Seq[Figure]) {
		val	changed	= changedFigures(figures, newFigures) map { _.globalBounds }
		val area	= rectanglesUnion(changed) map { _.toRectangle2D.getBounds }
		area foreach { repaint(_) }
		figures	= newFigures
	}
	
	// TODO slow
	private def changedFigures(oldFigures:Iterable[Figure], newFigures:Iterable[Figure]):Set[Figure] = {
		val	oldSet	= oldFigures.toSet
		val newSet	= newFigures.toSet
		(oldSet union newSet) diff (oldSet intersect newSet)
	}

	private def rectanglesUnion(bounds:Iterable[SgRectangle]):Option[SgRectangle]	=
			bounds.nonEmpty guard (bounds reduceLeft { _ union _ })
	
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
