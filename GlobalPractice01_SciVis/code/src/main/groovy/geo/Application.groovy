package geo

import groovy.transform.CompileStatic

import java.awt.BorderLayout
//import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
//import java.io.BufferedReader
import java.io.IOException
//import java.io.InputStreamReader

//import javax.swing.JButton
import javax.swing.JFrame
//import javax.swing.JList
import javax.swing.JPanel
//import javax.swing.JScrollPane
//import javax.swing.JTextField
import javax.swing.Timer
import com.jogamp.opengl.awt.GLCanvas

@CompileStatic
public class Application extends JFrame {
		
	private Scene scene
	private GLCanvas canvas	

	public Application() throws NumberFormatException, IOException {
		super("GeoViewer")

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		canvas = new GLCanvas()
		JPanel mainPanel = new JPanel(new BorderLayout())
		
		mainPanel.add(canvas)	
		getContentPane().add(mainPanel,BorderLayout.CENTER)
		setSize(600, 400)
	}


	public void run() throws IOException {
        
		canvas.addGLEventListener(scene)
	    canvas.addKeyListener(scene)
		setVisible(true)
		new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.display()
			}
		}).start()
	}


	public static void main(String[] args) throws Exception {
		
		Model model = new Model()
		model.load()
		
		Application app = new Application()
		app.scene = new Scene(model)
		app.run()	
	}

	
}
