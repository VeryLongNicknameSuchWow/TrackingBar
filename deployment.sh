#!/bin/bash
BUILD_NUMBER=$(ls /var/lib/jenkins/jobs/TrackingBar/builds/ | grep '^[0-9]\+$' | cut -c2- | sort -n | tail -n1 | sed 's|^|r|')
rm /home/minecraft/servers/1.12.2/plugins/trackingbar-*.jar
cp /var/lib/jenkins/jobs/TrackingBar/builds/$BUILD_NUMBER/archive/target/*.jar /home/minecraft/servers/1.12.2/plugins/