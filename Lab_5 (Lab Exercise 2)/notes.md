Start by choosing to use ParaView or VolView
============================================

-The guide says: Download a tool : ParaView or VolView
-FROM: http://vtk.1045678.n5.nabble.com/ParaView-vs-VolView-td5723772.html
    --One thing I noticed is that ParaView has seen several recent updates. Whereas, VolView seems to not have been updated in a while. Also, ParaView is running on VTK 6.0. Looks like VolView is still on VTK 5.0. And of course, the UI looks much better on ParaView than on VolView. Looks like both use the same renderers for volume rendering.
    --If you want to do 3D volume rendering, especially with medical images,
then VolView might be a good start. If you want to do scientific
visualization using a variety of data representations, then Paraview
is the one. Also, Paraview is under active development. I'm not sure
about the status of VolView. 
-According to wikipedia:
    --ParaView: https://en.wikipedia.org/wiki/ParaView
        ---Good review.
    --VolView: N/A
-WebPage:
    --ParaView: http://www.paraview.org/
        ---Seems nice and complete
    --VolView: https://www.kitware.com/volview/
        ---Seems good
-...
-I think that ParaView has a better review in several places.
-...
-After installing ParaView
    --Open a raw file and set it up. FROM: http://wiki.rac.manchester.ac.uk/community/ParaView/Tips/LoadImageStack
    --Then change the value in Display->Representation to "Volume".
-Follow the steps for Engine.raw and Tomato.raw.
-Finally a tried to view Present.dat but it didn't work:
    --Here we can see some info about this error: FROM: http://www.paraview.org/pipermail/paraview/2017-March/039664.html
        ---ParaView has not full support for Fluent files (cas + dat). It doesn't see all the variables (at least in versions up to 5.0.1 RC2 that I use, although I know that I should update to 5.2 or 5.3).
