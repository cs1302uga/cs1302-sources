#!/bin/bash -e

if [[ `git status --porcelain --untracked-files=no` ]]; then
    git status --porcelain
    echo "Cannot release because you have local modifications!"
    exit 1
fi

export MAVEN_OPTS="--add-opens=java.base/java.util=ALL-UNNAMED"
set -x

mvn -B clean
mvn -B versions:use-releases release:clean release:prepare
mvn -B release:perform
