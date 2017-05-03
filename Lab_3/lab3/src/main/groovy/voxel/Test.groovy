package voxel

public class Test {

	public static Model fileTest(String filename, float orig, float inc, List res,
								 boolean is16bits, boolean hasHeader) {

		URL path = this.getClass().getResource( "/models/"+filename )
		File file = new File(path.file)
		Model model = new Model(orig, orig, orig, inc, inc, inc)
		model.loadFromFile(file, res, is16bits, hasHeader)
		model.generateHistogram()
		return model
	}


	public static void main(String[] args) {

		// GOOD
		Model model = fileTest("Engine.raw", 0.0f, 1.0f, [256,256,256], false, false)

		// TODO: BAD, I don't know why, it should be the same than others 8 bits.
		//Model model = fileTest("Tomato.raw", 0.0f, 1.0f, [256,256,64], false, false)

		// TODO: BAD, maybe I'm reading 16 bits in a wrong way.
		//Model model = fileTest("present.dat", 0.0f, 1.0f, [246,246,221], true, true)

		// GOOD
		//Model model = fileTest("nucleon.raw", 0.0f, 1.0f, [41,41,41], false, false)

		// SEEMS GOOD, but I'm not sure.
		//Model model = fileTest("marschnerlobb.raw", 0.0f, 1.0f, [41,41,41], false, false)

		// GOOD
		//Model model = fileTest("fuel.raw", 0.0f, 1.0f, [64,64,64], false, false)


		Scene.show(model)

	}

}