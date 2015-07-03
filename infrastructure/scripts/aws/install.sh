#!/bin/sh

echo "=====> Stopping hackerbrasileiro service"
ssh -i /var/go/hackerbrasileiro-key.pem ubuntu@52.11.131.93 "service hackerbrasileiro stop"

echo "=====> Removing hackerbrasileiro jar"
ssh -i /var/go/hackerbrasileiro-key.pem ubuntu@52.11.131.93 "rm -rf app/hackerbrasileiro.jar"

echo "=====> Generate jar"
./gradlew hackerBrasileiroJar

echo "=====> Copy jar to app folder"
scp -i /var/go/hackerbrasileiro-key.pem hackerbrasileiro.jar ubuntu@52.11.131.93:app/

echo "=====> Removing generate.py file"
ssh -i /var/go/hackerbrasileiro-key.pem ubuntu@52.11.131.93 "rm -rf facemorpher/generate.py"

echo "=====> Adding generate.py file"
scp -i /var/go/hackerbrasileiro-key.pem facemorpher/generate.py ubuntu@52.11.131.93:app/facemorpher/

echo "=====> Starting hackerbrasileiro service"
ssh -i /var/go/hackerbrasileiro-key.pem ubuntu@52.11.131.93 "service hackerbrasileiro start"