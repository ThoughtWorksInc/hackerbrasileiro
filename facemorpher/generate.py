import facemorpher
import os

# Get a list of image paths in a folder
photo_path = os.environ['HACKERBRASILEIRO_PHOTO_PATH']
imgpaths = facemorpher.list_imgpaths(photo_path)

# To average, supply an array of face images:
result_path = os.environ['HACKERBRASILEIRO_FACEMORPHER_PATH'] + '/result.png'
facemorpher.averager(imgpaths, out_filename=result_path)
