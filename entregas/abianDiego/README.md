# Reto 001 - laFila

Resuelto con lo visto en PRG1 y PRG2: clases, arrays y `Math.random()`. La fila es un array de `Persona` gestionado por la clase `Fila`.

<div align=center>

|Fichero|Responsabilidad|
|-|-|
|[Simulacion.java](src/laFila/Simulacion.java)|`main`: lanza el reto base y el escenario completo.|
|[CentroComercial.java](src/laFila/CentroComercial.java)|Las reglas del centro comercial: llegadas, cajas, preferencia, coladas, parlantes y el informe.|
|[Fila.java](src/laFila/Fila.java)|Un array de `Persona` con su longitud: incorporar en una posición, retirar de una posición y consultar.|
|[Persona.java](src/laFila/Persona.java)|Lo que decide cada persona: si se aburre y si desiste al ver una fila larga.|
|[Circunstancia.java](src/laFila/Circunstancia.java)|Ninguna, embarazo, tercera edad o discapacidad.|

</div>

## Ejecución

Desde `src/`:

```bash
javac laFila/*.java
java -ea laFila.Simulacion
```

## Decisiones de diseño

- **Cada uno decide lo suyo.** Aburrirse y desistir son decisiones de la persona (`Persona.seAburre`, `Persona.desisteAlVerUnaFilaLarga`): la fila no se las impone.
- **La preferencia la da el centro comercial.** La persona solo tiene una circunstancia (embarazo, tercera edad, discapacidad); es la política del centro comercial (`tieneDerechoPreferente`) la que la convierte en preferente.
- **La fila no rompe sus propias reglas.** `Fila` solo sabe incorporar en una posición y retirar de una posición. Quien decide dónde se coloca un preferente o un colado es el centro comercial, no la fila.
- **Se pregunta antes de actuar.** Antes de atender, de colarse o de entregar compras se comprueba que haya gente en la fila; los métodos de `Fila` no se defienden, sino que lo exigen con `assert`.
- **La fila no tiene límite físico.** El límite de 30 es una política, no una restricción: el array interno duplica su capacidad cuando se llena.

## Supuestos (lo que el enunciado no fija)

<div align=center>

|Regla|Valor elegido|
|-|-|
|Llegada de una persona preferente, cada minuto|20 %|
|Alguien se cuela detrás de un conocido, cada minuto|10 %|
|Alguien entrega sus compras a otra persona de la fila y se va, cada minuto|5 %|
|Desistir al ver la fila con 30 personas o más|50 %|
|Personas que atiende la caja abierta por el aviso de los parlantes|5|

</div>

- Las reglas nuevas (aburrirse, preferentes, coladas, desistir, entregar compras y parlantes) solo se aplican en el escenario completo y a partir del minuto 20. El aburrimiento se pregunta en los minutos 20, 25, 30...
- "Pasen por esta caja en orden de fila": se abre una caja más que atiende a las primeras personas de la fila.

## Observación

Con las probabilidades del enunciado, el aburrimiento (30 % cada 5 minutos para quien lleva más de 8 minutos) vacía la fila tan rápido que esta rara vez llega a 25 o 30 personas. Por eso, en la mayoría de ejecuciones no hay avisos por los parlantes ni nadie desiste. Subiendo la probabilidad de llegada a 0.9 y quitando el aburrimiento, la fila crece y ambas reglas se activan.
