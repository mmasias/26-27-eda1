# laFila

> Restricciones: a resolver con lo visto en las asignaturas PRG1 & PRG2

Ibuprofeno Fernández durante el verano estuvo trabajando en el CCCF, controlando el acceso a la fila única antes de las cajas del centro comercial. Conforme llegaba gente, la iba situando al final de la fila, en el orden en que llegaban.

Cuando se abría una caja, la persona que estaba al frente de la fila pasaba a ser atendida y salía de la fila de forma definitiva.

## Reto base

Simule esta primera versión del escenario durante cuatro horas, con llegada de una persona cada minuto con probabilidad 0.6 y apertura de una caja libre cada minuto con probabilidad 0.4.

Registre, al cierre, cuantas personas fueron atendidas y cuantas quedaron en fila.

## Retos extendidos

A partir del minuto 20, además de llegar y ser atendida, a la gente en fila le puede pasar lo siguiente, en cualquier orden:

- Alguien que lleva mas de 8 minutos en fila sin ser atendido se aburre y se va, desde cualquier posición de la fila, no solo desde el frente (30% de probabilidad de aburrirse, cuestionado cada 5 minutos).
- Alguien con derecho de atención preferente (embarazo, tercera edad, discapacidad) se incorpora justo después de la última persona con ese mismo derecho que ya este en fila, o al frente si nadie en fila tiene ese derecho aún.
- Alguien se cuela detrás de un conocido que ya esta en la fila, en la posición inmediata siguiente a la de ese conocido, sin derecho alguno que lo justifique.

En el centro comercial la política es que una fila no puede tener más de 30 posiciones. Esto lógicamente no es bloqueante, por lo que al intentar incorporar a alguien (por llegada normal, colada licita o colada ilicita) con la fila superando este tamaño, la persona -al ver la cola tan larga- podría desistir y no incorporarse.

También puede darse que alguien le entrega sus compras a otra persona que ya esta en la fila.

Cada 15 minutos simulados, si la fila supera las 25 personas, se escucha por los parlantes "pasen por esta caja en orden de fila".

## Retos

- Simule el escenario completo (dos horas, con las reglas nuevas activas desde el minuto 20).
- Reporte, para cada minuto, el tamaño (longitud) de la cola, asumiendo que cada persona ocupa 1 metro de longitud.

## Se debe entregar

- El código fuente del programa, obligatorio.
- Todos los demás artefactos, optativos.
