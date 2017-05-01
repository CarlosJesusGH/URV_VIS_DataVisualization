package voxel

abstract class Model {
	
	  int resX, resY, resZ
	  float incX, incY, incZ,minX, minY, minZ
	  int[][][] voxels

	  Model(float minX,float minY,float minZ,float incX,float incY,float incZ){

		this.incX=incX
		this.incY=incY
		this.incZ=incZ
		this.minX=minX
		this.minY=minY
		this.minZ=minZ
	  }

	  void setInc(float[] inc){
		  this.incX=inc[0]
		  this.incY=inc[1]
		  this.incZ=inc[2]
	  }

	  void setMin(float[] min){
		  this.minX=min[0]
		  this.minY=min[1]
		  this.minZ=min[2]
	  }
	  
	  int getVoxel(int vx, int vy, int vz){
		  return (voxels[vx][vy][vz])  
	  }

}
