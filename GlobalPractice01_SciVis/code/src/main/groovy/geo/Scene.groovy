package geo

import geo.technique.TransferFunc
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener

import javax.swing.JFrame
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

import com.jogamp.opengl.GL2
import com.jogamp.opengl.glu.GLU

//@CompileStatic
public class Scene extends JFrame implements GLEventListener, KeyListener {

	private static final long serialVersionUID = 1L
	private static GLU glu

	private Model model
	private TransferFunc fun

	public Scene(Model model){
		
		this.model = model
		fun = new TransferFunc()
	}
	
	public void init(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2()  // get the OpenGL 2 graphics context
		glu = new GLU()
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA)
	    gl.glEnable( GL2.GL_BLEND )
		gl.setSwapInterval(1)
		gl.glClearColor(1.0f, 1.0f, 1.0f, 0f)  	
	}	

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		if (height == 0) height = 1
		GL2 gl = drawable.getGL().getGL2()  // get the OpenGL 2 graphics context
        gl.glViewport(0, 0, width, height)    
		gl.glMatrixMode(GL2.GL_PROJECTION)
		gl.glLoadIdentity()						

		//System.out.println(model.geopotential.get(1).get(0)[1]+"x"+model.geopotential.get(2).get(0)[1])
		glu.gluOrtho2D(0, model.geopotential.get(1).get(0)[1], 0, model.geopotential.get(2).get(0)[1])

		gl.glMatrixMode(GL2.GL_MODELVIEW)
	}

   public void display(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2()  // get the OpenGL 2 graphics context

		// Clears the screen and depth buffer.
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT)
		
		gl.glLoadIdentity()					
		model.drawModel(gl)
		fun.draw(gl, model.getGeopotential())
	}
	
	public void keyPressed(KeyEvent arg0) {
		 
			char key = arg0.getKeyChar()
			
			switch(key) {
				  case 'a': model.incInstant()
				  break
				  case 'z': model.decInstant()
				  break
				  case 'q': fun.toggleAlpha()
				  break
				  case 'l': fun.toggleLegend()
				  break
				  case 'r': fun.reset()
					  // Also draw first instant from geopotential data set.
					  model.geopotential.clear()
					  model.currentInstant = 0
					  model.readDataFile(model.instants[0], model.geopotential)
				  break
				  case '1': fun.toggleMinRed()			  
				  break
				  case '2': fun.toggleMaxRed()			  
				  break
				  case '3': fun.toggleMinBlue()			  
				  break
				  case '4': fun.toggleMaxBlue()				  
				  break
				  case '5': fun.toggleMinGreen()			  
				  break
				  case '6': fun.toggleMaxGreen()			  
				  break
				  
				  case 27:
				    System.exit(0)    
				  break
			  }		
 
	}

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}

	public void dispose(GLAutoDrawable arg0) {}
}
