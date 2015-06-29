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
sudo pip install pillow --upgrade
sudo pip install facemorpher
sudo pip install numpy --upgrade
sudo pip install scipy --upgrade

#Replace macosx binary to linux binary
sudo wget https://github.com/alyssaq/face_morpher/raw/binaries/ubuntu/stasm_util
sudo cp stasm_util /usr/local/lib/python2.7/dist-packages/facemorpher/bin/stasm_util
sudo rm -rf stasm_util
