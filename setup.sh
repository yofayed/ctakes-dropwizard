#!/bin/bash

wget "http://downloads.sourceforge.net/project/ctakesresources/ctakes-resources-3.2.1.1-bin.zip?r=&ts=1461058782&use_mirror=iweb"
unzip ctakes-resources-3.2.1.1-bin.zip
mv ctakes-resources-3.2.1.1-bin/resources resources
rm ctakes-resources-3.2.1.1-bin.zip
echo "Downloaded  ctakes-resources"

export "CTAKES_DRP_BASEDIR"=`pwd`

mvn package
echo "package built"
echo "Run Server using the following command: java -Dctakes.umlsuser=<username> -Dctakes.umlspw=<passwd> -jar target/claims-nlp.0.0.1-SNAPSHOT.jar server"
