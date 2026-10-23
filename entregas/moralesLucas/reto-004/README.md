# Estructuras utilizadas y justificación

| **Dato** | **Decisión** | **Justificación** | **Alternativa descartada** | **Limitación asumida** |
|---|---|---|---|---|
| Alumnos presentes en el aula | Lista | El número de alumnos puede cambiar y necesitamos comprobar quién está presente antes de permitirle votar o presentarse. | Un array, porque tiene un tamaño establecido y resulta menos flexible para añadir o eliminar alumnos. | Para localizar a un alumno hay que recorrer los elementos de la lista. |
| Candidaturas durante la inscripción | Lista | Permite agregar o eliminar candidatos mientras el plazo de inscripción permanece abierto. | Un array, porque no conocemos de antemano cuántas personas se presentarán. | Cuando se cierra la inscripción, hay que pasar los candidatos a un array. |
| Candidatos definitivos | Array de `String` | Al terminar el plazo, el número de candidatos queda fijado y sus nombres ya no necesitan modificarse. | Una lista, porque ya no es necesario cambiar el número de elementos. | Es necesario copiar los nombres de los candidatos al array al finalizar la inscripción. |
| Papeletas pendientes de contar | Cola | Los votos se extraen en el mismo orden en que se introdujeron, siguiendo el funcionamiento FIFO. | Una pila, porque recuperaría primero la última papeleta recibida. | Una vez extraída una papeleta, deja de estar disponible en la cola. |
| Nombres de candidatos y votos en blanco o nulos | Array de `String` | Todas las opciones posibles se conocen antes de empezar el recuento y pueden almacenarse en posiciones fijas. | Una lista, porque no necesitamos incorporar nuevas opciones durante la votación. | Para encontrar un candidato hay que comprobar las posiciones del array. |
| Cantidad de votos por opción | Array de `int` | Cada posición almacena el número de votos de una opción y permite aumentar o reducir el contador. | Una lista, porque no es necesario crear ni eliminar contadores durante el recuento. | La posición de cada contador debe corresponder con la opción almacenada en el otro array. |

## Funcionamiento general

1. Se registran los alumnos presentes en clase.
2. Se gestionan las candidaturas y se obtiene el listado definitivo.
3. Los votos se guardan en una cola y se extraen siguiendo el orden de llegada.
4. Cada papeleta se clasifica como voto a un candidato, voto en blanco o voto nulo.
5. Se actualizan los contadores, se muestran los resultados y se anuncia si existe un ganador o un empate.

## Clases del programa

- **`Ejemplo`:** ejecuta la simulación y coordina las distintas operaciones.
- **`List`:** gestiona los alumnos presentes y las candidaturas mediante una lista enlazada.
- **`Node`:** representa cada nodo y mantiene la referencia al siguiente elemento.
- **`Queue`:** almacena las papeletas pendientes de contar mediante una cola.
- **`Pizarra`:** registra los votos, muestra el recuento y proclama el resultado.
