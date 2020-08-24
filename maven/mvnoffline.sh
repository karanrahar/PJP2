#!bin/bash
cd Demo
mvn dependency:go-offline
mvn -o clean install
read -p "Press [Enter] to exit"
