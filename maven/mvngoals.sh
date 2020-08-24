#!/bin/bash
echo "Enter phase"
read Phase
cd Demo
mvn help:describe -Dcmd=$Phase
read -p "Press [Enter] to exit"
