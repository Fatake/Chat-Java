#!/bin/bash
echo "Compilando servidor"
javac -encoding "UTF-8" -d class Servidor.java

echo "Ejecutando Servidor"
java -cp class Servidor
pause