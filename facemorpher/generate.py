import facemorpher

# Get a list of image paths in a folder
imgpaths = facemorpher.list_imgpaths('photos')

# To average, supply an array of face images:
facemorpher.averager(imgpaths, out_filename='facemorpher/result.png')

