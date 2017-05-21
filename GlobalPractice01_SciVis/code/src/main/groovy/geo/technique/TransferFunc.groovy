package geo.technique

import com.jogamp.opengl.GL2

//public class TransferFunc implements Technique {
public class TransferFunc {

	private float minRed, minBlue, minGreen, maxRed, maxBlue, maxGreen

	public void reset() {
		//TODO: calculate
	}
	
	public void toggleMinRed() {
		//TODO: calculate
	}

	public void toggleMaxRed() {
		//TODO: calculate
	}

	public void toggleMinBlue() {
		//TODO: calculate
	}

	public void toggleMaxBlue() {
		//TODO: calculate
	}

	public void toggleMinGreen() {
		//TODO: calculate
	}

	public void toggleMaxGreen() {
		//TODO: calculate
	}	

    public void draw(GL2 gl, List<List<Float []>> geopotencials){
		//TODO: calculate

	}


	private float[] calcColor(float xmin, float xmax, float value){
		
		float[] colors = [0.0f, 0.0f, 0.0f]

		//TODO: calculate

		return colors
	}


}
