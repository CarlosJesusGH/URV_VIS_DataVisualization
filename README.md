SESSION 1/LAB 1
===============

This week we will start working with OpenGL, using these resources :
1.1 Introduction to Computer Graphics
1.2 Introduction to OpenGL
Lab Guide
lab1.zip

And following these objectives :

    -Key principles of OpenGL
    -How to work with JOLG
    -How to code with Groovy
    -How to build / execute a Gradle project

Indeed, this first session is just an introduction, in order to take a first look of foundations that we will need in the first part of this course.

    NOTES:
        To run the project from terminal just do:
        gradle compileGroovy
        gradle run


SESSION 2/LAB 2
===============

This second week we will study the first topic of SciVis, starting with the data volumes :

2.1 Data Volumes (slides 1-29)
Lab Guide (updated)
lab2.zip

The objectives of this session are :

    -Understand the basics of SciVis
    -Understand the voxel concept
    -First contact with Groovy/JOGL
    -Generate a voxel volume

In addition, in this session we will introduce the first lab exercise, that can be done during the one hour of class.


SESSION 3/LAB 3
===============

This third week we will continue the first topic of SciVis, the data volumes :

2.1 Data Volumes (slides 30-59)
Lab Guide (updated)
lab3.zip

The objectives of this session are :

    -How to load voxel data
    ?-Study optimization structures
    -Learn the Histogram concept
    ?-Understand the Transfer function


SESSION 4/LAB 4
===============

Dear Students,

This week we will start the second topic of SciVis, the calculated data :

2.2 Calculated Data
Lab Guide (updated)
lab4.zip

The objectives of this session are :

Understand the Surface Extraction concept
Learn the main algorithms : 
    marching squares
    marching cubes
    dividing cubes

In addition, in this session we will introduce the first practise of the course. 
This task includes the documentation and the skeletor project. 
The delivery task is now available to upload your work : 
https://moodle.urv.cat/moodle/mod/assign/view.php?id=1859862

And the forum thread for this Global Practise 1 : 
https://moodle.urv.cat/moodle/mod/forum/discuss.php?d=353174

    Notes:
        -Marching squares explanation: https://www.youtube.com/watch?v=6ovo5b6vLKA
        -Marching squares wiki: https://en.wikipedia.org/wiki/Marching_squares
        -Marching cubes wiki: https://en.wikipedia.org/wiki/Marching_cubes
        -Dividing cubes video: https://www.youtube.com/watch?v=-WZfdmrADR4
        
    Questions:
        -What is that we need to do in the lab session?
        -What does it mean "extend this OpenGL code in order to recollect the outstanding information"?
        -When is performed the compression? (calculate ... the % of void voxels before and after the compression).

SESSION 5/LAB 5
===============

Dear Students,

This week we will finish with the SciVis part with this last topic, direct visualisation :

    2.3 Data Visualisation
    Lab Guide (updated)

The objectives of this session are :

    Learn the ray tracing algorithm
    Learn the projective methods 

In addition, in this fifth lab session we will work the second lab exercise. The delivery task is now available to upload your report : 
https://moodle.urv.cat/moodle/mod/assign/view.php?id=1859860
Any questions about this exercise will be attended in its own thread : 
https://moodle.urv.cat/moodle/mod/forum/discuss.php?d=354089
Finally, check the latest updates in the Support Material, with a set of short videos showing the most important concepts in this course.

    Notes:
        -Ray tracing algorithm wiki: https://en.wikipedia.org/wiki/Ray_tracing_(graphics)
            --Very good explanatory video about Ray Tracing: https://www.youtube.com/watch?v=Ahp6LDQnK4Y
            --In computer graphics, ray tracing is a technique for generating an image by tracing the path of light through pixels in an image plane and simulating the effects of its encounters with virtual objects.
            --The ray tracing algorithm builds an image by extending rays into a scene.
            --For each sample we calculate: the property value, the gradient, colour it deserves, composition samples.
            --PovRay is a free software to render images using Ray Tracing, check a video using it: https://www.youtube.com/watch?v=WZrY32jSjvU
            --A guy writing a Ray Tracer in 30 minutes using only C++: https://www.youtube.com/watch?v=ARn_yhgk7aE
        -Here some nice videos explaining Ray tracing algorithm:
            --https://www.youtube.com/watch?v=H-CMDCmehy4
            --https://www.youtube.com/watch?v=bN8AV_x4BXI
        -Z-buffering wiki: https://en.wikipedia.org/wiki/Z-buffering
            --The Z letter in Z-buffering means the distance from the camera point of view until the point in every pixel.
            --The Buffer word in Z-buffering means the array of distances onw for every pixel in the screen.
        -Here some nice videos explaning de Z-buffer method:
            --https://www.youtube.com/watch?v=yhwg_O5HBwQ            
            --https://www.youtube.com/watch?v=L6U746cO-yQ
        -Projective methods are based on adapting the algorithm Z-Buffer technique. 
        -Some examples of projective methods are:
            --Sorted iteration - ?
            --Splatting. link: https://en.wikipedia.org/wiki/Volume_rendering#Splatting - ? not sure how it works ? 
        -About the colors, check:
            --Transfer function: it's a function used to transform data into an expression based on color and opacity, usually represented as an RGBA value, this is RGB+Alpha.
            --Transfer function - Great explanation in this class, last 10 minutes (including marching squares and some other definitions) https://youtu.be/FYAJQgYdeqQ?t=42m52s
            --Transfer function: a video about how to use this method to analyse a volume.
            --Probabilistic models: The colour of a given point is weighted according to the colours of different tissues considering the probability that the point of this material was.
        -Very important about ray composition:
            --Each ray takes a set of samples
            --Each sample calculates:
            --•the property value
            --•gradient
            --•colour it deserves
            --•composition samples
        -Different models of colour composition:
            --MIP (maximum colour value)
            --FTB (front to back)
            --BTF (back to front)
