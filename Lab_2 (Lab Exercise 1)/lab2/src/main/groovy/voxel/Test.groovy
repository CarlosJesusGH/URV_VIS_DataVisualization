package voxel

class Test {

	static Model phantomTest() {

		float orig = 0, inc = 1
		float[][] points = [
				[0.0f, 189.0f, 0.0f, 140.0f, 230.0f],
				[189.0f,	0.0f, 0.0f, 60.0f, 200.0f],
				[189.0f, 189.0f, 189.0f, 150.0f, 170.0f],
				[0.0f, 0.0f, 189.0f, 90.0f, 50.0f],
				[92.0f, 92.0f, 92.0f, 70.0f, 250.0f]]

		PhantomModel phantom = new PhantomModel(orig, orig, orig, inc, inc, inc, points)
		phantom.createVoxelModel()
		phantom
	}

	static void main(String[] args) {

		Model model = phantomTest()
		Scene.show(model)

	}

}