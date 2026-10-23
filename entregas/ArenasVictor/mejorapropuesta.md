# Reto 004 - Mejora de propuesta

## Tabla de decisiones

<div align=center>

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
|-|-|-|-|-|
| Alumnos presentes | Lista | Llegan alumnos tarde y otros se van, y solo pueden proponerse y votar los presentes. El tamaño cambia. | Array con un indicador de presencia: guarda también a los ausentes. | Comprobar si alguien está presente obliga a recorrer la lista. |
| Quién ya entregó papeleta | Otra lista, separada de la de presentes | Cada alumno vota una vez y las papeletas no llevan nombre, así que hace falta un registro aparte para detectar el doble voto. | Marcarlo en la lista de presentes: mezcla dos datos distintos. | Buscar en ella también es recorrerla. Si alguien vota y se va, sigue aquí aunque ya no esté en presentes. |
| Candidaturas con el plazo abierto | Lista | Mientras el plazo está abierto se proponen y se retiran candidatos, así que el tamaño cambia. A quien no está presente se le rechaza y no entra en la lista. | Array desde el principio: no se sabe cuántos candidatos habrá hasta cerrar el plazo. | Al cerrar hay que copiar los candidatos a un array. Si un candidato se va del aula, nada actualiza esta lista. Los rechazos no se guardan. |
| Candidaturas con el plazo cerrado | Array de nombres | Al cerrar el plazo los candidatos quedan fijados y ya no hay altas ni bajas. | Seguir con la lista: ya no hace falta poder añadir ni quitar. | Si alguien se retirara después del cierre, el array no lo permitiría; el enunciado no lo contempla. |
| Papeletas pendientes de lectura | Cola | El presidente las deja formando un montón y el secretario las toma una a una, así que solo se usa un extremo. **Supuesto:** el enunciado no dice de qué extremo las toma y el recuento sale igual con cola o con pila; elijo la cola porque entiendo mejor los nodos con referencia al primero y al último. | Pila: leería primero la última recogida y encaja con el montón. La descarto por criterio propio, no porque el enunciado la excluya. Array o lista: dan acceso a posiciones que no se usan. | Una papeleta leída sale de la cola; para revisarla habría que guardarla aparte. La cola necesita además guardar el último nodo. |
| Votos por opción (pizarra) | Dos arrays paralelos (nombres y votos), con blanco y nulo en las dos últimas posiciones | El tamaño es fijo desde el cierre y, como en la pizarra del enunciado, blanco y nulo son dos columnas más. Sumar o borrar un voto es cambiar un contador en una posición conocida. | Lista: para llegar a la posición i hay que recorrer i nodos. Pila o cola: no se llega a una posición sin sacar las anteriores. Blanco y nulo en contadores aparte: se alejan de la pizarra del enunciado. | Al proclamar solo se recorren las posiciones de candidatos, porque blanco y nulo no pueden ganar. Ese recorrido también detecta el empate. Buscar a un candidato por su nombre es recorrer el array, y un nulo solo se detecta al no encontrar el nombre. |

</div>

## Cambios respecto a mi propuesta

<div align=center>

| Decisión | Qué hago | Por qué |
|-|-|-|
| Array para los candidatos | Lo mantengo, pero solo con el plazo cerrado | Al cerrar el plazo los candidatos quedan fijados y sumar un voto es acceder a una posición. |
| Justificación del array | La cambio | Decir que rinde algo mejor no indica en qué operación; la cambio por propiedades del dato. |
| Alcance del array | Lista durante el plazo y copia al array al cerrar | El enunciado dice que durante el plazo se puede retirar un candidato y proponer otros, y un array no lo permite. Adopto la decisión de los ejemplos por ese argumento. |
| Papeletas pendientes de lectura | Añado una cola | Mi propuesta no cubría este dato. El montón del enunciado apunta a la pila; elijo la cola con un supuesto declarado y reconozco que es la decisión más débil. |
| Quién ya entregó papeleta | Lo añado | Cada alumno vota una vez y las papeletas no llevan nombre; hace falta un registro aparte. |
| Blanco y nulo | Los añado dentro del array de votos | El enunciado los pone como columnas de la pizarra; declaro que al proclamar solo se recorren los candidatos. |

</div>
