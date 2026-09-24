# Reto 001 - laFila

Simulacion de la fila unica del CCCF (Centro Comercial), con la version
base y la version extendida descritas en el enunciado.

## Estructura

    reto001-laFila/
    |-- src/
    |   |-- Persona.java
    |   |-- Fila.java
    |   |-- Simulador.java
    |   |-- Main.java
    |-- .gitignore

- Persona: datos de cada persona en la fila (id, minuto de llegada, si tiene atencion preferente).
- Fila: estructura de la fila y las operaciones sobre ella (agregar al final, al frente, despues de una posicion, atender al frente, remover por abandono).
- Simulador: contiene toda la logica de las reglas (llegada, apertura de caja, abandono, atencion preferente, colarse, capacidad maxima, anuncio por parlantes).
- Main: punto de entrada; ejecuta primero el reto base (4 horas) y luego el reto extendido (2 horas).

## Como ejecutar

Desde la carpeta src/:

    javac *.java
    java Main

## Supuestos asumidos

El enunciado no especifica ciertas probabilidades, por lo que se asumieron
los siguientes valores en las constantes al inicio de Simulador.java:

- Probabilidad de que una persona que llega sea preferente: 15%
- Probabilidad de que una persona que llega se cuele: 15%
- Probabilidad de desistir al intentar incorporarse con la fila en su capacidad maxima (30): 50%

La entrega de compras a otra persona ya en la fila no se modelo como un
evento aparte, ya que quien entrega nunca llega a incorporarse a la fila:
no afecta ni el tamano ni el conteo de atendidos.

## Resultados esperados

- Reto base: cantidad de personas atendidas y cantidad de personas que quedaron en fila al cierre de las 4 horas.
- Reto extendido: longitud de la fila (en metros, 1 persona = 1 metro) reportada minuto a minuto, mas el resultado final.
