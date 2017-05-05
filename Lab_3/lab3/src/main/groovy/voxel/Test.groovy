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

		// GOOD. Although, is not showing the same it says in the guide (it did before, why now it's not?)
		Model model = fileTest("Engine.raw", 0.0f, 1.0f, [256,256,256], false, false)

		// GOOD
		//Model model = fileTest("Tomato.raw", 0.0f, 1.0f, [256,256,64], false, false)

		// GOOD
		//Model model = fileTest("present.dat", 0.0f, 1.0f, [246,246,221], true, true)

		// GOOD
		//Model model = fileTest("nucleon.raw", 0.0f, 1.0f, [41,41,41], false, false)

		// GOOD
		//Model model = fileTest("marschnerlobb.raw", 0.0f, 1.0f, [41,41,41], false, false)

		// GOOD
		//Model model = fileTest("fuel.raw", 0.0f, 1.0f, [64,64,64], false, false)


		Scene.show(model)

	}

}