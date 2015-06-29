import facemorpher

# Get a list of image paths in a folder
imgpaths = facemorpher.list_imgpaths('./../src/main/resources/photos')

# To average, supply an array of face images:
facemorpher.averager(imgpaths, out_filename='result.png')

