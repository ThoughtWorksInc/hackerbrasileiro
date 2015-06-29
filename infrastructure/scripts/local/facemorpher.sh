#!/bin/sh

echo "=====> Installing unzip"
sudo apt-get --yes install unzip

echo "=====> Installing python-pip and python-dev"
sudo apt-get --yes install python-pip
sudo apt-get --yes install python2.7-dev

echo "=====> Installing python-opencv"
sudo apt-get --yes install libopencv-dev python-opencv

echo "=====> Installing python-numby python-scipy python-matplotlib ..."
sudo apt-get --yes install python-numpy python-scipy python-matplotlib ipython ipython-notebook python-pandas python-sympy python-nose

echo "=====> Installing docopt"
sudo pip install docopt

echo "=====> Installing pillow"
sudo pip install pillow

echo "=====> Installing facemorpher"
sudo pip install facemorpher

echo "=====> Installing numpy"
sudo pip install numpy

echo "=====> Installing scipy"
sudo pip install scipy

echo "=====> Replace macosx binary to linux binary"
sudo wget https://github.com/alyssaq/face_morpher/raw/binaries/ubuntu/stasm_util
sudo cp stasm_util /usr/local/lib/python2.7/dist-packages/facemorpher/bin/stasm_util
sudo rm -rf stasm_util
