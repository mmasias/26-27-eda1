# Explicación de la elección en Estructura de Datos y Algoritmos

## Propuesta

- Creamos un array con los nombres de los candidatos (`arrayCandidatos`) y otro para registrar los votos emitidos y el candidato al que corresponde cada uno (`arrayVotos`).

## Justificación del arrayVotos (int)

- Almacenamos en este array los votos de cada candidato.
- El tamaño del array será igual a la cantidad de candidatos más dos, para incluir los votos nulos y las abstenciones.
- Las primeras posiciones del array corresponderán a los votos nulos y a las abstenciones, en ese orden. Como la cantidad de candidatos puede variar y no tiene un límite definido, así podremos identificar primero los votos que no corresponden a ningún candidato, incluso si hubiera 20 candidatos.

## Justificación del arrayCandidatos (String)

- Almacenamos en este array los nombres de los candidatos.
- El tamaño inicial del array dependerá de la cantidad de candidatos.







