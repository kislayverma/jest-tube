#!/bin/sh

if [ -f /var/www/html/tester.php ]
then
    sed -i "s/$sendMails = true/$sendMails = false/g" /var/www/html/tester.php
    sed -i "s/server:7034/silkroutedeployment/g" /var/www/html/tester.php
fi
