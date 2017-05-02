package voxel

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

