#!/bin/sh

rm -f tpid

nohup java -jar platform-boot.war --spring.config.location=application.yml> /dev/null 2>&1 &

echo $! > tpid

echo Start Success!