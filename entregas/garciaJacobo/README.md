# Explicación de elección en estructura de datos y algoritmos

| **Dato** | **Decisión** | **Justificación** | **Alternativa descartada** | **Limitación asumida** |
|---|---|---|---|---|
| Alumnos presentes en el aula | Lista | El número de alumnos presentes puede cambiar porque algunos llegan tarde y otros se van. Además, necesitamos comprobar si un alumno está presente para poder votar o proponerse como candidato. | Array, porque tendríamos que tener un tamaño fijado desde el principio aunque los alumnos puedan entrar y salir. | Para comprobar si un alumno está presente tenemos que recorrer la lista. |
| Candidaturas mientras el plazo está abierto | Lista | Durante el plazo pueden aparecer nuevos candidatos y otros pueden retirar su candidatura, por lo que el número de candidatos puede cambiar. | Array, porque no sabemos desde el principio cuántos candidatos habrá al cerrar el plazo. | Cuando se cierre el plazo tendremos que pasar las candidaturas de la lista al array. |
| Candidaturas al cerrar el plazo | Array de `String` | Cuando se cierra el plazo, los candidatos quedan fijados y ya no se pueden añadir ni retirar. Por eso el número de candidatos ya no cambia. | Mantener la lista, porque ya no necesitamos que el número de candidatos pueda cambiar. | Tenemos que copiar las candidaturas de la lista al array cuando se cierre el plazo. |
| Papeletas recogidas pendientes de lectura | Cola | El secretario va tomando las papeletas una a una y podemos mantener el mismo orden en el que fueron recogidas. El orden no cambia el resultado del recuento. | Pila, porque leería primero la última papeleta recogida. Array o lista, porque permiten acceder a posiciones que no necesitamos. | Cuando una papeleta sale de la cola ya no podemos volver a ella directamente. |
| Nombres de candidatos, blanco y nulo | Array de `String` | Cuando se cierra el plazo ya conocemos todos los candidatos y también tenemos las opciones de blanco y nulo. Por tanto, el número de posiciones ya no cambia. | Lista, porque ya no necesitamos añadir ni quitar opciones durante el recuento. | Para encontrar un candidato por su nombre puede ser necesario recorrer el array. |
| Recuento de votos | Array de `int` | Cada posición guarda los votos de una opción. Cuando un alumno vota aumentamos su contador y si nos equivocamos podemos quitar un voto. | Lista, porque durante el recuento no necesitamos añadir ni eliminar contadores. | Los dos arrays tienen que estar relacionados: la posición de un candidato debe coincidir con la posición de sus votos. |

## Supuestos

- Un alumno que no está presente no puede proponerse como candidato ni votar.
- Si un alumno intenta votar dos veces, el segundo voto no se acepta.
- Si una papeleta tiene el nombre de un candidato que se ha retirado o que no forma parte de las candidaturas definitivas, se considera voto nulo.
- Una papeleta vacía se considera voto en blanco.
- Si el recuento de un candidato se anota mal, se puede corregir quitando el palito correspondiente.
- Para decidir el ganador solo se tienen en cuenta los candidatos definitivos; los votos en blanco y nulos no pueden hacer que un candidato gane.
- Si dos o más candidatos tienen el mismo número máximo de votos, se considera que hay empate.
