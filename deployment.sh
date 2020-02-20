#!/bin/bash
rm /home/minecraft/servers/1.12.2/plugins/trackingbar-*.jar
cp /var/lib/jenkins/jobs/TrackingBar/builds/$BUILD_NUMBER/archive/target/*.jar /home/minecraft/servers/1.12.2/plugins/