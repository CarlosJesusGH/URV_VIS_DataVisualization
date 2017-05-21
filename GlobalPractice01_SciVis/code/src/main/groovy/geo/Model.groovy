package geo

import com.jogamp.opengl.GL
import com.jogamp.opengl.GL2
import com.jogamp.opengl.glu.GLU
import groovy.transform.CompileStatic

//@CompileStatic
public class Model {

	List contour = []
	List meridians = []
	List parallels = []
	List points = []
	List geopotential = []

	List instants = []
	int currentInstant=0

	void load() throws IOException {
		
		String source="/data/europe/euro_contour.dat"
		readDataFile(source, contour)
		source="/data/europe/euro_meridians.dat"
		readDataFile(source, meridians)
		source="/data/europe/euro_parallels.dat"
		readDataFile(source, parallels)
		source="/data/europe/euro_points.dat"
		readDataFile(source, points)				
		
		source="/data/geopotential"
		instants = listFiles(source)
		readDataFile(instants[0], geopotential)
	}
	
	void incInstant() {
		
		int posMax = instants.size()
		  
		if(currentInstant+1==posMax){
			  currentInstant=0				  
		}
		else currentInstant++
		
		geopotential.clear()
		try {
            readDataFile(instants[currentInstant], geopotential)
		} catch (IOException e) {
			e.printStackTrace()
		}
	}
	
	void decInstant() {
		
		int posMax = instants.size()
		  
		if(currentInstant-1<0) {
			currentInstant=posMax-1				  
		 }
		 else currentInstant--
		  
		geopotential.clear()
		try {
			readDataFile(instants[currentInstant], geopotential)
		} catch (IOException e) {
			e.printStackTrace()
		}
	}
	
	void drawModel(GL2 gl) {
		
		drawCoordPoints(gl, points, [0.0f, 0.5f, 0.0f])
		drawCoordPoints(gl, contour, [0.6f, 0.2f, 0.0f])
		drawCoordLines(gl, meridians, [0.0f, 0.5f, 0.0f])
		drawCoordPoints(gl, parallels, [0.0f, 0.5f, 0.0f])
	}

	private void drawCoordPoints(GL2 gl, List points, List colors) {

		points.each { temp ->
			temp.each { temp2 ->
		    	gl.glBegin(GL2.GL_POINTS)
			    glColor3f(gl, colors[0], colors[1], colors[2])
			    gl.glVertex3f(temp2[0], temp2[1],0.0f)
			    gl.glEnd()  
		    }			
		}		
	}



	private void drawCoordLines(GL2 gl, List points, List colors) {
					
		points.each { temp ->
			gl.glBegin(GL2.GL_LINES)
            temp.each { temp2 ->
				glColor3f(gl, colors[0],colors[1],colors[2])
			    gl.glVertex3f(temp2[0], temp2[1], 0.0f)
		    }
		    gl.glEnd()  
		}		
	}

	//float conversion GL methods -> compile static checking
	private void glColor3f(GL2 gl2, x, y, z) {
		gl2.glColor3f((float) x, (float) y, (float) z)
	}
	
	private void readDataFile(String source, List target) throws IOException{

		  URL path = this.getClass().getResource( source )
		  println "$source -> $path"
		  File file = new File(path.file)
		  FileInputStream fstream = new FileInputStream(file)
		  DataInputStream din = new DataInputStream(fstream)
		  BufferedReader br = new BufferedReader(new InputStreamReader(din))
		  
		  List temp = []
		  int components=0
		  int counter=0
		  boolean noCounter=false
		  
		  String strLine
		  while ((strLine = br.readLine()) != null){
			  strLine=strLine.trim() 
			  String[] coords=strLine.split(" ")
			  if(coords[0] != "DSAA"){
				  if(coords.length==1){
					  components=Integer.parseInt(coords[0])
					  noCounter=true					  
				  }else{
					  float[] data = new float[coords.length]
					  for(int i=0;i<coords.length;i++){
						  data[i]=Float.parseFloat(coords[i])
					  }
					  temp.add(temp.size(),data)
					  counter++
				  }		  	  
				  if(components==counter){
					  target.add(target.size(),temp)
					  temp= []
					  counter=0
				  }
				  if(!noCounter){
					  target.add(target.size(),temp)
					  temp= []
				  }
			  }
		  }			  	
  
		  br.close()
		  din.close()
		  fstream.close()
	}
	
	private static String[] listFiles(String route) {
		
		List routes

		URL path = this.getClass().getResource( route )
		File folder = new File(path.file)
		File[] listOfFiles = folder.listFiles() 
		  
		  routes = new String[listOfFiles.length]
		  for(int i=0; i<listOfFiles.length; i++){
			  String absPath=listOfFiles[i].toString()
              routes[i] = absPath.split("/resources/main").last()
		  }
		  
		return routes 
	}
		
	
}
