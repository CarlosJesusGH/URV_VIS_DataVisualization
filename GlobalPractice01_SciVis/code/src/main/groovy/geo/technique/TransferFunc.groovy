package geo.technique


import com.jogamp.opengl.GL2
import com.jogamp.opengl.util.awt.TextRenderer

import java.awt.Color
import java.awt.Font


//public class TransferFunc implements Technique {
public class TransferFunc {

	private float minRed = 0, minBlue = 0, minGreen = 0, maxRed = 1, maxBlue = 1, maxGreen = 1, alpha = 0.5
	private boolean useLegend = true

	public void reset() {
		minRed = 0
		minBlue = 0
		minGreen = 0
		maxRed = 1
		maxBlue = 1
		maxGreen = 1
		alpha = 0.5
		useLegend = true
		println("All values reset")
	}
	
	public void toggleMinRed() {
		if(minRed < maxRed)
			minRed += 0.1
		else
			minRed = 0
		println("minRed = " + minRed)
	}

	public void toggleMaxRed() {
		if(minRed < maxRed)
			maxRed -= 0.1
		else
			maxRed = 1
		println("maxRed = " + maxRed)
	}

	public void toggleMinBlue() {
		if(minBlue < maxBlue)
			minBlue += 0.1
		else
			minBlue = 0
		println("minBlue = " + minBlue)
	}

	public void toggleMaxBlue() {
		if(minBlue < maxBlue)
			maxBlue -= 0.1
		else
			maxBlue = 1
		println("maxBlue = " + maxBlue)
	}

	public void toggleMinGreen() {
		if(minGreen < maxGreen)
			minGreen += 0.1
		else
			minGreen = 0
		println("minGreen = " + minGreen)
	}

	public void toggleMaxGreen() {
		if(minGreen < maxGreen)
			maxGreen -= 0.1
		else
			maxGreen = 1
		println("maxGreen = " + maxGreen)
	}

	public void toggleAlpha() {
		if(alpha > 0)
			alpha -= 0.1
		else
			alpha = 1
		println("alpha = " + alpha)
	}

	public void toggleLegend() {
		useLegend = !useLegend
		println("Legend = " + useLegend)
	}

    public void draw(GL2 gl, List<List<Float []>> geopotencials){
		int nCols = geopotencials[0][0][0]
		int nRows = geopotencials[0][0][1]
		float xStep = (geopotencials[1][0][1] - geopotencials[1][0][0]) / (nCols - 1)
		float yStep = (geopotencials[2][0][1] - geopotencials[2][0][0]) / (nRows - 1)
		float vMin = geopotencials[3][0][0]
		float vMax = geopotencials[3][0][1]
		for (int i = 0; i < nRows - 1; i++) {
			for (int j = 0; j < nCols - 1; j++) {
				def colors = [calcColor(vMin, vMax, geopotencials[4+i+1][0][j])]
				colors.add(calcColor(vMin, vMax, geopotencials[4+i][0][j]))
				colors.add(calcColor(vMin, vMax, geopotencials[4+i][0][j+1]))
				colors.add(calcColor(vMin, vMax, geopotencials[4+i+1][0][j+1]))
				def points = [[(float) (xStep * j), (float) (yStep * (i+1))]]
				points.add([(float) (xStep * j), (float) (yStep * i)])
				points.add([(float) (xStep * (j+1)), (float) (yStep * i)])
				points.add([(float) (xStep * (j+1)), (float) (yStep * (i+1))])
				drawCoordQuads(gl, points, colors)
			}
		}
		// Draw the legend
		if(useLegend) {
			// Main white square
			drawCoordQuads(gl, [[0, 3], [0, 0], [7, 0], [7, 3]],
					[[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]])
			// Border lines
			drawCoordLines(gl, [[0, 3], [7, 3]], [0,0,0,1])
			drawCoordLines(gl, [[7, 3], [7, 0]], [0,0,0,1])
			// Add text
			TextRenderer textRenderer = new TextRenderer(new Font("Verdana", java.awt.Font.BOLD, 20));
			textRenderer.beginRendering(900, 700);
			textRenderer.setColor(Color.BLACK);
			textRenderer.setSmoothing(true);
			textRenderer.draw("Legend:", (int) (2), (int) (90));
			textRenderer.draw(vMin.toString(), (int) (2), (int) (60));
			textRenderer.draw(vMax.toString(), (int) (160), (int) (60));
			textRenderer.endRendering();

			//Draw color bar
			int nBars = 10
			for (int i = 0; i < nBars; i++) {
				float xStep2 = 7 / nBars
//				drawCoordQuads(gl, [[0, 3], [0, 0], [7, 0], [7, 3]], [[0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1]])
				def pointsLeg = [[(float) (xStep2 * i), 1.5f], [(float) (xStep2 * i), 0.2f],
									[(float) (xStep2 * (i+1)), (float) 0.2f], [(float) (xStep2 * (i+1)), 1.5f]]
				def colorsLeg = [calcColor(0, 1, i * (1/nBars))]
				colorsLeg.add(calcColor(0, 1, i * (1/nBars)))
				colorsLeg.add(calcColor(0, 1, (i+1) * (1/nBars)))
				colorsLeg.add(calcColor(0, 1, (i+1) * (1/nBars)))
				drawCoordQuads(gl, pointsLeg, colorsLeg)
			}
		}
	}

	private ArrayList calcColor(float xmin, float xmax, float value){
		float[] colors = [0.0f, 0.0f, 0.0f]
		float normVal = (value - xmin) / (xmax - xmin)
		colors = [((maxRed - minRed) * normVal) + minRed,
				  ((maxGreen - minGreen) * normVal) + minGreen,
				  ((maxBlue - minBlue) * normVal) + minBlue,
					alpha]
		return colors
	}

	private void drawCoordPoints(GL2 gl, List point, List color) {
		gl.glBegin(GL2.GL_POINTS)
		glColor4f(gl, color[0], color[1], color[2],1)
		gl.glVertex3f(point[0], point[1],0.0f)
		gl.glEnd()
	}

	private void drawCoordLines(GL2 gl, List points, List color) {
		gl.glBegin(GL2.GL_LINES)
		glColor4f(gl, color)
		gl.glVertex3f(points[0][0], points[0][1], 0.0f)
		gl.glVertex3f(points[1][0], points[1][1], 0.0f)
		gl.glEnd()
	}

	private void drawCoordQuads(GL2 gl, List points, List colors) {
		gl.glBegin(GL2.GL_QUADS)
		glColor4f(gl, colors[0])
		gl.glVertex2f(points[0][0], points[0][1])
		glColor4f(gl, colors[1])
		gl.glVertex2f(points[1][0], points[1][1])
		glColor4f(gl, colors[2])
		gl.glVertex2f(points[2][0], points[2][1])
		glColor4f(gl, colors[3])
		gl.glVertex2f(points[3][0], points[3][1])
		gl.glEnd()
	}

	//float conversion GL methods -> compile static checking
	private void glColor4f(GL2 gl2, color) {
		gl2.glColor4f((float) color[0], (float) color[1], (float) color[2], (float) color[3])
	}
}
