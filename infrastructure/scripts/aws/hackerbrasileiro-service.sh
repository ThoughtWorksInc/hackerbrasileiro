#!/bin/sh

#!/bin/sh
### BEGIN INIT INFO
# Provides:          hackerbrasileiro
# Required-Start:    $local_fs $remote_fs $network $syslog
# Required-Stop:     $local_fs $remote_fs $network $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# X-Interactive:     true
# Short-Description: Start/stop hackerbrasileiro server
### END INIT INFO

case $1 in
    start)
        echo "Starting hackerbrasileiro..."
        if [ ! -f /home/ubuntu/app/pid ]; then
            nohup java -Dserver.port=5000 -jar /home/ubuntu/app/hackerbrasileiro.jar /home/ubuntu/app 2>> /home/ubuntu/app/logs.txt >> /home/ubuntu/app/logs.txt &
            echo $! > /home/ubuntu/app/pid
            echo "hackerbrasileiro started ..."
        else
            echo "hackerbrasileiro is already running ..."
        fi
    ;;
    stop)
        if [ -f /home/ubuntu/app/pid ]; then
            PID=$(cat /home/ubuntu/app/pid);
            echo "Stopping hackerbrasileiro..."
            kill $PID;
            echo "hackerbrasileiro stopped ..."
            rm /home/ubuntu/app/pid
        else
            echo "hackerbrasileiro is not running ..."
        fi
    ;;
    restart)
        if [ -f /home/ubuntu/app/pid ]; then
            PID=$(cat /home/ubuntu/app/pid);
            echo "Stopping hackerbrasileiro...";
            kill $PID;
            echo "hackerbrasileiro stopped ...";
            rm /home/ubuntu/app/pid

            echo "Starting hackerbrasileiro..."
            nohup java -Dserver.port=5000 -jar /home/ubuntu/app/hackerbrasileiro.jar /home/ubuntu/app 2>> /home/ubuntu/app/logs.txt >> /home/ubuntu/app/logs.txt &
            echo $! > /home/ubuntu/app/pid
            echo "hackerbrasileiro started ..."
        else
            echo "hackerbrasileiro is not running ..."
        fi
    ;;
esac
