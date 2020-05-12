#!/bin/bash
echo "Compilando servidor"
javac -d class Servidor.java

echo "Ejecutando Servidor"
java -cp class Servidor
pause