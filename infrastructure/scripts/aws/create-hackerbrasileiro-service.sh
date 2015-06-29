#!/bin/sh

sudo mv service/hackerbrasileiro-service.sh /etc/init.d/hackerbrasileiro
sudo chmod +x /etc/init.d/hackerbrasileiro
sudo update-rc.d hackerbrasileiro defaults