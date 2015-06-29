#!/bin/sh

#git
sudo apt-get --yes install git

#openjdk
sudo apt-get --yes install software-properties-common python-software-properties
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get --yes install openjdk-8-jdk
sudo update-alternatives --config java

#unzip
sudo apt-get --yes install unzip

#facemorpher dependencies
sudo apt-get --yes install python-pip
sudo apt-get --yes install python2.7-dev
sudo apt-get --yes install python-numpy python-scipy python-matplotlib ipython ipython-notebook python-pandas python-sympy python-nose
sudo pip install docopt
sudo pip install pillow
sudo pip install facemorpher
sudo pip install numpy
sudo pip install scipy

#Recompile stasm_util
sudo apt-get --yes install cmake
sudo wget http://www.milbo.org/stasm-files/4/stasm4.1.0.tar.gz
tar --extract --file=stasm4.1.0.tar.gz stasm4.1.0
cd stasm4.1.0
git clone https://github.com/alyssaq/stasm_build.git
patch apps/appmisc.cpp < stasm_build/patches/appmisc.cpp.20140201.diff
patch apps/shapefile/shapefile.cpp < stasm_build/patches/shapefile.cpp.20140201.diff
rm -r stasm_build/patches
sudo cp stasm_build/* .
mkdir build && cd build
cmake ../
make
sudo cp stasm_util /usr/local/lib/python2.7/dist-packages/facemorpher/bin/stasm_util
