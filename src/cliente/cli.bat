#!/bin/bash
echo "Compilando Cliente"
javac -encoding "UTF-8" -d class ClienteGUI.java

echo "Ejecutando Cliente"
java -cp class ClienteGUI
pause