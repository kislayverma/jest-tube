#!/bin/sh

if [ -f /var/www/html/tester.php ]
then
    sed -i "s/$sendMails = false/$sendMails = true/g" /var/www/html/tester.php
    sed -i "s/silkroutedeployment/server:7034/g" /var/www/html/tester.php
fi
