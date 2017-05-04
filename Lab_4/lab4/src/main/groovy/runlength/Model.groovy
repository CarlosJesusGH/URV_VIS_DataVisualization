package runlength

public class Model {	
	
	  protected int resX, resY, resZ
	  protected float incX, incY, incZ,minX, minY, minZ
	  protected int[][][] voxels
	  
	  private boolean loaded=false  
	  protected int[] histogram
	  protected int maxHist,minHist,freqVmin
	  
	  public Model(float minX,float minY,float minZ,float incX,float incY,float incZ){
		  		  
		  this.incX=incX
		  this.incY=incY
		  this.incZ=incZ
		  this.minX=minX
		  this.minY=minY
		  this.minZ=minZ		  
	  }
	  
	  public void setInc(float[] inc){
		  this.incX=inc[0]
		  this.incY=inc[1]
		  this.incZ=inc[2]
	  }
	  
	  public void setMin(float[] min){
		  this.minX=min[0]
		  this.minY=min[1]
		  this.minZ=min[2]
	  }	 


	  public int getHistValue(int index){
		  return histogram[index]
	  } 

	  public int getVoxel(int vx, int vy, int vz){	
		  return (voxels[vx][vy][vz])  
	  }
	  
	  public int getBlankPercent() {
		  return (freqVmin*100)/(resX*resY*resZ)
	  }	  
		  	  
	  public boolean loadFromFile(File file, List res, boolean is16bits, boolean hasHeader) {
		  
		  resX=res[0]
		  resY=res[1]
		  resZ=res[2]
		  
		  voxels = new int[resX][resY][resZ]
		  histogram=new int[(is16bits)?65531:256]
		  
		  try {
			  
			DataInputStream dis = new DataInputStream (new FileInputStream(file))
			
			if (hasHeader) {
				if (is16bits) {
					resX=dis.readUnsignedByte()
					if (is16bits) resX = (dis.readUnsignedByte() << 8) | resX
					resY=dis.readUnsignedByte()
					if (is16bits) resY = (dis.readUnsignedByte() << 8) | resY
					resZ=dis.readUnsignedByte()
					if (is16bits) resZ = (dis.readUnsignedByte() << 8) | resZ
				}
				System.out.println("Dimensions : ("+resX+","+resY+","+resZ+")")
			}
			
			for (int k=0; k<resZ; k++){
									
			for (int i=0; i<resX; i++) {
				for (int j=0; j<resY; j++) {
				
						voxels[i][j][k]=dis.readUnsignedByte()						
						if (is16bits) voxels[i][j][k] = (dis.readUnsignedByte() << 8) | voxels[i][j][k]
						//if (is16bits) dis.readUnsignedByte()
					}		
				}
			}
			
			dis.close()
			
		 
			} catch (Exception e) {
				e.printStackTrace()
				return false
			}
		  
		  this.loaded=true
		  
		  return loaded 
	  }
	  
	  public void generateHistogram() {

			for (int i = 0; i < resX; i++)
				for (int j = 0; j < resY; j++)
					for (int k = 0; k < resZ; k++) {

						histogram[this.voxels[i][j][k]]++
						if (voxels[i][j][k] < minHist)
							minHist = voxels[i][j][k]
						if (voxels[i][j][k] > maxHist)
							maxHist = voxels[i][j][k]
						if (voxels[i][j][k] == 0)
							freqVmin++

					}

			//for (int i = 0 i < histogram.length i++)
			//	System.out.println("Property value(" + i + ") -> " + histogram[i])

			System.out.println("Histogram max(" + maxHist + ") min(" + minHist
					+ ") blank(" + getBlankPercent() + "%)")

		}

}
