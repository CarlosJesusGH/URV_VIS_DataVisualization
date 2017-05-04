package runlength

import javax.swing.*
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.awt.GLCanvas

import javax.swing.JFrame
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.Dimension

import com.jogamp.opengl.GL2
import com.jogamp.opengl.glu.GLU

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

import com.jogamp.opengl.util.FPSAnimator
 
public class Scene extends GLCanvas implements GLEventListener, KeyListener {
	
	private static final long serialVersionUID = -8844447290517224033L

	// Define constants for the top-level container
   private static String TITLE = "Voxel Viewer"  // window's title
   private static int CANVAS_WIDTH = 400  // width of the drawable
   private static int CANVAS_HEIGHT = 400 // height of the drawable
   private static final int FPS = 60 // animator's target frames per second
   
   private static Model model
   private static int slice = 0
   
   public Scene() {
	      this.addGLEventListener(this)
	      this.addKeyListener(this)
}
   
   public static void show (Model model) {
	  
	  Scene.model = model
	  CANVAS_WIDTH = model.resX
	  CANVAS_HEIGHT = model.resY
	   
      // Run the GUI codes in the event-dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            // Create the OpenGL rendering canvas
            GLCanvas canvas = new Scene()
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT))
 
            // Create a animator that drives canvas' display() at the specified FPS.
            final FPSAnimator animator = new FPSAnimator(canvas, FPS, true)
 
            // Create the top-level container
            final JFrame frame = new JFrame() // Swing's JFrame or AWT's Frame
            frame.getContentPane().add(canvas)
            frame.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  // Use a dedicate thread to run the stop() to ensure that the
                  // animator stops before program exits.
                  new Thread() {
                     @Override
                     public void run() {
                        if (animator.isStarted()) animator.stop()
                        System.exit(0)
                     }
                  }.start()
               }
            })
            frame.setTitle(TITLE)
            frame.pack()
            frame.setVisible(true)
            animator.start() // start the animation loop
         }
      })
   }
   
   private GLU glu
 
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2()      // get the OpenGL graphics context
      glu = new GLU()                         // get GL Utilities
      gl.glViewport(0,0,CANVAS_WIDTH,CANVAS_HEIGHT)

      gl.glMatrixMode(GL2.GL_PROJECTION)
      gl.glLoadIdentity()
      glu.gluOrtho2D(model.minX,(float)model.resX,model.minY,(float)model.resY)
      gl.glMatrixMode(GL2.GL_MODELVIEW)
 
   }
 

   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2()  // get the OpenGL 2 graphics context
      
	  gl.glMatrixMode(GL2.GL_PROJECTION)
	  gl.glLoadIdentity()
	  glu.gluOrtho2D(0, width, 0, height)
	  gl.glMatrixMode(GL2.GL_MODELVIEW)
     
   }
 

   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2()  // get the OpenGL 2 graphics context
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT) // clear color and depth buffers
      
      gl.glLoadIdentity()  // reset the model-view matrix
 
      for (int j=0; j<model.resX-1; j++) {
    	    for (int i=0; i<model.resY-1; i++) {
	    
    	      gl.glBegin(GL2.GL_POLYGON)
    	      	drawVertex(gl,i,j)
    	      	drawVertex(gl,i+1,j)
    	      	drawVertex(gl,i+1,j+1)
    	      	drawVertex(gl,i,j+1)
    	      gl.glEnd()
    	      
    	    }
      } 
      
      gl.glFlush()
      
   }
   
   public void drawVertex(GL2 gl, int i, int j) {	

	      float voxel = model.getVoxel(i, j, slice)
	      float color=voxel/model.maxHist
	      //System.out.println(voxel+" / "+model.getMaxHist()+" -> "+color)
	      gl.glColor3f(color,color,color)
	      
	      float px=model.minX + model.incX*i
	      float py=model.minY + model.incY*j
	      
	      gl.glVertex3f(px, py, 0.0f)
   }

    public void renderText(GL2 gl) {

        renderer.beginRendering(CANVAS_WIDTH, CANVAS_HEIGHT);
        renderer.setColor(1f, 1f, 1f, 0.9f);
        renderer.draw("Runlenght activated", CANVAS_WIDTH/2-20, CANVAS_HEIGHT-10);
        renderer.endRendering();
    }

    public void drawRunlength(GL2 gl){

        List<List<RunlengthModel.RunlengthEntry>> runlength = model.getRunlength();

        float color, px, py;
        int k = slice, num;

        List<RunlengthModel.RunlengthEntry> rowList = runlength.get(k);

        for(RunlengthModel.RunlengthEntry entry : rowList) {

            color = (float) entry.getColor();
            float[] rgbRet = calcColor((color/255));
            num = entry.getNumber();

            //System.out.print(runlength[r][k].getNumber()+":"+runlength[r][k].getColor()+",");
            if (num > 0){

                px=model.getMinX()+model.getIncX()*entry.getX();
                py=model.getMinY()+model.getIncY()*entry.getY();

                gl.glPointSize(3);
                gl.glBegin(GL_POINTS);
                gl.glColor3f(rgbRet[0], rgbRet[1], rgbRet[2]);
                gl.glVertex3f(px,py,0.0f);
                gl.glEnd();

                num--;
            }
            //System.out.println();
        }

    }

    private float[] calcColor(float value) {

        float R = 0.0f;
        float G = 0.0f;
        float B = 0.0f;
        int[] rgb = [255,255,255]
        float[] rgbRet = new float[3];

        if(value >= 0.0 && value <= 0.10) {
            R = rgb[0] * 0.0f;
            G = rgb[1] * 0.0f;
            B = rgb[2] * 0.0f;

        } else if(value > 0.10 && value <= 0.20) {
            R = rgb[0] * 1.0f;
            G = (float) Math.ceil(rgb[1] / 2);
            B = rgb[2] * 0.0f;

        } else if(value > 0.20 && value <= 0.30) {
            R = rgb[0] * 1.0f;
            G = rgb[1] * 1.0f;
            B = rgb[2] * 0.0f;

        } else if(value > 0.30 && value <= 0.40) {
            R = (float) Math.ceil(rgb[0] / 2);
            G = rgb[1] * 1.0f;
            B = rgb[2] * 0.0f;

        } else if(value > 0.40 && value <= 0.50) {
            R = rgb[0] * 0.0f;
            G = rgb[1] * 1.0f;
            B = rgb[2] * 0.0f;

        } else if(value > 0.50 && value <= 0.60) {
            R = rgb[0] * 0.0f;
            G = rgb[1] * 1.0f;
            B = (float) Math.ceil(rgb[2] / 2);

        } else if(value > 0.60 && value <= 0.70) {
            R = rgb[0] * 0.0f;
            G = rgb[1] * 1.0f;
            B = rgb[2] * 1.0f;

        } else if(value > 0.70 && value <= 0.80) {
            R = rgb[0] * 0.0f;
            G = (float) Math.ceil(rgb[1] / 2);
            B = rgb[2] * 1.0f;

        } else if(value > 0.80 && value <= 0.90) {
            R = rgb[0] * 0.0f;
            G = rgb[1] * 0.0f;
            B = rgb[2] * 1.0f;

        } else if(value > 0.90 && value <= 1.0) {
            R = (float) Math.ceil(rgb[0] / 2);
            G = rgb[1] * 0.0f;
            B = rgb[2] * 1.0f;
        }

        rgbRet[0] = R/255;
        rgbRet[1] = G/255;
        rgbRet[2] = B/255;

        return rgbRet;
    }


    public void keyPressed(KeyEvent e) {
	   if (e.getKeyCode() == KeyEvent.VK_UP) {
		   slice++
		   if (slice>model.resZ) slice = model.resZ
	   }
	   else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		   slice--
		   if (slice<0) slice = 0
	   }	
	   System.out.println("Showing slice "+slice)
   }	   
	
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
   public void dispose(GLAutoDrawable drawable) {}
   
}

