package scscene

import java.awt.{ Graphics2D, RenderingHints }

object GraphicsUtil {
	def highQuality(gg:Graphics2D) {
		gg setRenderingHint (RenderingHints.KEY_ANTIALIASING, 			RenderingHints.VALUE_ANTIALIAS_ON)
		gg setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING,		RenderingHints.VALUE_TEXT_ANTIALIAS_ON)	// VALUE_TEXT_ANTIALIAS_GASP
		gg setRenderingHint (RenderingHints.KEY_INTERPOLATION,			RenderingHints.VALUE_INTERPOLATION_BICUBIC)
		gg setRenderingHint (RenderingHints.KEY_COLOR_RENDERING,		RenderingHints.VALUE_COLOR_RENDER_QUALITY)
		gg setRenderingHint (RenderingHints.KEY_ALPHA_INTERPOLATION,	RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY)
	}
	
	def lowQuality(gg:Graphics2D) {
		gg setRenderingHint (RenderingHints.KEY_ANTIALIASING, 			RenderingHints.VALUE_ANTIALIAS_OFF)
		gg setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING,		RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
		gg setRenderingHint (RenderingHints.KEY_INTERPOLATION,			RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR)
		gg setRenderingHint (RenderingHints.KEY_COLOR_RENDERING,		RenderingHints.VALUE_COLOR_RENDER_SPEED)
		gg setRenderingHint (RenderingHints.KEY_ALPHA_INTERPOLATION,	RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED)
	}
}
