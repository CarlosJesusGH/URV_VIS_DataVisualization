package voxel

class PhantomModel extends Model {

	private HeatPoint[] heatPoints;

    PhantomModel(float minX, float minY, float minZ, float incX,
                      float incY, float incZ, float[][] points) {

		super(minX, minY, minZ, incX, incY, incZ);

		resX = resY = resZ = 256;
		
		this.heatPoints = new HeatPoint[points.length];
		for (int n = 0; n < points.length; n++) {
			heatPoints[n] = new HeatPoint(points[n]);
		}
	}

	private float distance(float x1, float y1, float z1, 
						   float x2, float y2, float z2) {

		float d = 0.0f;
		
		//TODO: IMPLEMENT THIS
		// Compute distance from heat source to voxel point
		d = Math.pow((x2-x1),2) + Math.pow((y2-y1),2) + Math.pow((z2-z1),2)
		d = Math.sqrt(d)
		
		d
	}

	private int contribution(float x, float y, float z, int p) {

		int v = 0;
		HeatPoint h = heatPoints[p];

		float d = distance(x, y, z, h.x, h.y, h.z);
		
		//TODO: IMPLEMENT THIS
		// Compute heat contribution from source to voxel
		if(d < h.radius){ // if d>h.radius the contribution 'v' is equal to 0
			float stepDiff = h.value / h.radius
			v = h.value - (stepDiff * d)
		}
		
		v
	}

	void createVoxelModel() {

		voxels = new int[resX][resY][resZ];

		//TODO: IMPLEMENT THIS
		// Set values to every voxel
		for (int n = 0; n < this.heatPoints.length; n++) {
			println("n="+n)
			for (int k=0; k<resZ; k++)
				for (int i=0; i<resX; i++)
					for (int j=0; j<resY; j++)
						// Add the contribution from every heat source
						voxels[i][j][k] = voxels[i][j][k] + contribution(i, j, k, n)
		}
	}

	
	class HeatPoint {
		
		private float x, y, z, radius, value;

		HeatPoint(float[] floats) {
			
			x = floats[0];
			y = floats[1];
			z = floats[2];
			radius = floats[3];
			value = floats[4];
		}
	}

}
