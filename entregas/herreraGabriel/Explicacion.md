## Explicacion

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
| :--- | :--- | :--- | :--- | :--- |
| Registro de alumnos | Lista enlazada | Los alumnos pueden llegar tarde o marcharse en cualquier momento, requiriendo inserciones y borrados dinámicos durante el proceso. | Array estático (su tamaño fijo impide agregar o eliminar alumnos en tiempo real sin dejar huecos). | Buscar a un alumno para verificar si está presente requiere un recorrido secuencial. |
| Candidaturas | Lista enlazada | Es necesario poder retirar o rechazar candidatos durante el proceso, modificando la estructura dinámicamente. | Array estático (obligaría a gestionar posiciones nulas o redimensionar el arreglo constantemente). | Buscar al ganador al final exige un recorrido secuencial completo de los nodos. |
| Urna de votos a papel | Cola | Las papeletas se leen y se recuentan en el orden exacto en el que fueron depositadas en la urna. | Pila (alteraría el orden temporal real, procesando primero los últimos votos depositados). | No se puede acceder a un voto intermedio sin desencolar todos los anteriores. |
| Historial de recuento | Pila | Para corregir un error en la mesa, se necesita deshacer la última acción de recuento registrada. | Lista enlazada (añade complejidad de recorrido innecesaria para un mecanismo de retroceso simple). | Solo se puede deshacer de forma directa el evento inmediatamente anterior. |

### Cambios respecto a mi propuesta
Abandonar los arreglos estáticos (`Candidato[]`, `boolean[] yaVoto`) ya que su tamaño fijo impide gestionar eventos dinámicos como alumnos que entran o salen, y candidaturas retiradas. Adoptar listas enlazadas para el registro y los candidatos. Se incorpora una cola para la urna (garantizando el orden de llegada) y una pila como registro de acciones para poder deshacer el último voto contabilizado en caso de un error humano en la mesa.