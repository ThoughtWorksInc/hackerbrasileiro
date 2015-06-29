#!/bin/sh

echo "=====> Stopping hackerbrasileiro service"
ssh -i /var/go/hackerbrasileiro-key.pem ubuntu@52.11.131.93 "service hackerbrasileiro stop"

echo "=====> Generate jar"
./gradlew hackerbrasileiroJar

echo "=====> Copy jar to app folder"
scp -i /var/go/hackerbrasileiro-key.pem hackerbrasileiro.jar ubuntu@52.11.131.93:app/

echo "=====> Starting hackerbrasileiro service"
ssh -i /var/go/hackerbrasileiro-key.pem ubuntu@52.11.131.93 "service hackerbrasileiro start"