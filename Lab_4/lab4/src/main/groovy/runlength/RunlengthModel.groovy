package runlength

public class RunlengthModel extends Model {

	private int propRange
	private List<List<RunlengthEntry>> runlength
	
	public RunlengthModel(float minX, float minY, float minZ, float incX,
			float incY, float incZ, int propRange) {

		super(minX, minY, minZ, incX, incY, incZ)
						
		this.propRange = propRange				
	}
	
	public int getPropValue() {
		return propRange
	}

	public List<List<RunlengthEntry>> getRunlength() {
		return runlength
	}
	
	 public void encode(boolean is16bits){
			
		int row = 0, equals = 0, colorChange, currentColor
		
		int maxValue = (is16bits)?65531:256
		
		runlength = new Vector<List<RunlengthEntry>>()		

		for(int k=0; k<resZ; k++) {
			
		   row = 0
		   List<RunlengthEntry> rowList = new Vector<RunlengthEntry>()
		    
		   currentColor=voxels[0][0][k]
		   
		   for (int j=0; j<resX; j++) {
		   
			   for (int i=0; i<resY; i++) {
		       
				   colorChange = voxels[i][j][k]
		           
				   if (colorChange == currentColor)  equals++		                
		           
				   else {
		           
					   rowList.add(new RunlengthEntry(currentColor, equals, i, j))		               
					   row++

		               currentColor = colorChange
		               equals = 1
		            }
		        }
		    }
		   runlength.add(rowList)
		    
		   System.out.println("Slice "+k+" r = "+row+" \n")		        
		  	        
	    }    
		
	 }
	 
	 class RunlengthEntry {
			
			private int color, number
			private int x,y

			public RunlengthEntry(int color, int number, int x, int y) {
				this.color = color
				this.number = number
				this.x = x
				this.y = y
			}

			public int getX() {
				return x
			}

			public int getY() {
				return y
			}

			public int getColor() {
				return color
			}

			public int getNumber() {
				return number
			}
			
			
		}



}
