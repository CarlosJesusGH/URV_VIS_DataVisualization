Data analysis and visualization using public posts data from stackexchange found at:
    https://archive.org/download/stackexchange/

# General Pre-requisites:
    1. Python2:
        *snap
            follow tutorial con website.
    2. Python3:
        *conda package manager
        *jupyter
            conda install -n venv_conda jupyter
        *pandas
            conda install -n venv_conda pandas
        *mpld3
            conda install -n venv_conda mpld3
        *matplotlib
        *numpy
        *seaborn
      
---
# Getting and processing the data

## Snap Pre-requisites:
    -Use a python virtual environment with version 2.7.
    -Install snap.py (it says it is only for CentOS but it works for Ubuntu as well.
    -After setup, copy the new created files snap.py and _snap.so to the running code directory.
    
## First tests:
    -Download www15-code and www15-data files used in the slides in the java example.
    -Run the stackoverflow.sh script using the packages www15-code and www15-data.
    -Check the resulting files java-posts.txt, qa.txt and stats.txt.
    
## Following tests:
    -Try creating some plots using the data obtained in the last tests.
    -Try running the other scripts (commented into stackoverflow.sh) using the updated Posts.xml file, it is really heavy but probably will return very interesting information.
    
## Final steps
    -Complete the jupyter notebook
    -Make a latex report
    -Upload project
