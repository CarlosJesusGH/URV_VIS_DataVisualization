package voxel

public class Model {	
	
	  int resX, resY, resZ
	  float incX, incY, incZ,minX, minY, minZ
	  int[][][] voxels
	  
	  private boolean loaded=false  
	  private int[] histogram
	  int maxHist,minHist,freqVmin
	  
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
			
			//HINT : (dis.readUnsignedByte() << 8) | dis.readUnsignedByte() 
						
			if (hasHeader) {
				//TODO IMPLEMENT THIS	
				System.out.println("Dimensions : ("+resX+","+resY+","+resZ+")")
			}
			
			//TODO IMPLEMENT THIS
			
			dis.close()
			
		 
			} catch (Exception e) {
				e.printStackTrace()
				return false
			}
		  
		  this.loaded=true
		  
		  return loaded 
	  }

}
