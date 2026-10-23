# Reto 004 - Refactoring de la propuesta

---

## Instrucciones:

> Restricciones: Java *plain vanilla*. Las estructuras (lista, pila, cola) se implementan, como las vistas en clase; no se usa `java.util`.

Parta de su [propuesta al ejercicio de Elecciones](https://github.com/mmasias/26-27-eda1/pulls?q=is%3Apr+state%3Aopen+base%3Aelecciones) y de los [ejemplos de abordaje](https://github.com/puntoReflex/pyElecciones) de una solución con las estructuras que estamos estudiando. El enunciado del proceso electoral es el de esos ejemplos.

## Reto base

### Revise su propuesta

Reescriba su propuesta como una tabla, con una fila por cada dato del proceso:

<div align=center>

|Dato|Decisión|Justificación|Alternativa descartada|Limitación asumida|
|-|-|-|-|-|

</div>

- La justificación nombra la propiedad del dato que lleva a la estructura, no la definición de la estructura. "Uso una cola porque es FIFO" no justifica nada; "uso una cola porque las papeletas se leen en el orden en que se recogieron" sí.
- Cada justificación se apoya en un hecho del enunciado o en un supuesto que usted declara explícitamente.
- Toda decisión tiene una alternativa descartada, con su motivo, y una limitación que asume. "Ninguna" no es una limitación.

Añada una sección **Cambios respecto a mi propuesta**: qué decisiones mantiene, cuáles cambia y por qué. Mantener una decisión también se justifica. Si adopta una decisión de los ejemplos, explique qué argumento le hizo cambiar.

### Simule el proceso

Desarrolle un programa que simule el proceso electoral con la arquitectura de su tabla: cada fila corresponde a una estructura del código y cada estructura del código, a una fila de la tabla.

La simulación incluye, como mínimo:

- un alumno que llega tarde y otro que se marcha;
- una candidatura retirada y otra rechazada;
- una papeleta en blanco y otra nula;
- un intento de votar de alguien que no está presente;
- un intento de votar dos veces;
- la corrección de una anotación errónea en el recuento;
- la proclamación, detectando el empate.

Si su tabla afirma un coste o una limitación, la simulación lo muestra o el código indica dónde se produce.

## Retos extendidos

- Reporte, al cierre, la abstención: cuántos alumnos presentes no entregaron papeleta. Indique de qué estructuras obtiene el dato y por qué no puede contarse leyendo papeletas.
- Elija una decisión de los ejemplos de abordaje, o de la propuesta de un compañero, con la que no esté de acuerdo, y rebátala: qué hecho del enunciado no atiende, qué alternativa no consideró o qué limitación no declaró.

## Se debe entregar

- La propuesta revisada, obligatoria: tabla y sección de cambios.
- El código fuente, obligatorio: las estructuras y una clase `Ejemplo` que ejecute la simulación mostrando cada evento.
- Todos los demás artefactos, optativos.

La propuesta pesa más que el código: un programa que funciona con una arquitectura sin justificar no supera el reto.

Prepárese para defender su propuesta: en particular, por qué cada estructura y no la alternativa que descartó.

---

## Pasos:

1. Se crearon las Clases Nodo y Lista con sus metodos.

2. Se han creado las clases Stack y Pizarra con sus metodos para facilitar la pila de papeletas y la anotacion de los candidatos.

3. Se ha creado la clase Ejemplo para verificar que el codigo funciona como debe, con las ideas que pide el reto extendido.