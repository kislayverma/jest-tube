#!/bin/sh

rm -rf $1/src/main/tomcatconf/profiles/local/*
rm -rf $1/src/main/tomcatconf/profiles/qa/*
rm -rf $1/src/main/tomcatconf/profiles/stg/*
rm -rf $1/src/main/tomcatconf/profiles/perf/*
rm -rf $1/src/main/tomcatconf/profiles/prod/*
rm -rf $1/src/main/tomcatconf/profiles/prod1/*
rm -rf $1/src/main/tomcatconf/profiles/prod2/*
rm -rf $1/src/main/tomcatconf/profiles/devint/*

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'prod1' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/prod1

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'prod2' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/prod2

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'qa' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/qa

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'local' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/local

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'devint' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/devint

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'stg' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/stg

python /myntra/myntra-ops/releases/current/bin/yamlConfigReader.py --env 'perf' --yamldir $1/src/main/tomcatconf/yml-files --tpldir $1/src/main/tomcatconf/yml-files --outputdir $1/src/main/tomcatconf/profiles/perf
