#!/bin/bash
BUILD_NUMBER=$(exec ls /var/lib/jenkins/jobs/TrackingBar/builds/ | sed 's/\([0-9]\+\).*/\1/g' | sort -n | tail -1)
rm /home/minecraft/servers/1.12.2/plugins/trackingbar-*.jar
cp /var/lib/jenkins/jobs/TrackingBar/builds/$BUILD_NUMBER/archive/target/*.jar /home/minecraft/servers/1.12.2/plugins/