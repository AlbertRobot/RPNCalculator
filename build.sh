#!/usr/bin/env bash

mvn clean package

rm -rf output
mkdir output
mv target/RPNCalculator-bin.tar.gz output/