#!/usr/bin/env bash

mvn clean package \
   && cp target/l13-1.0-SNAPSHOT.war /home/user/jetty/jetty-distribution-9.4.9.v20180320/webapps/root.war