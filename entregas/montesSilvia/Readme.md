Reglas que habría que imponer en un array utilizando una lista:
Tamaño fijo: establecer una longitud inicial y no permitir añadir ni eliminar elementos, como ocurre en un array tradicional.

Acceso mediante índices: permitir consultar y modificar los elementos mediante una posición, usando métodos como get(indice) y set(indice, valor).

Control de límites: impedir el acceso a índices negativos o iguales o superiores a la longitud.

Orden de los elementos: mantener cada elemento en su posición hasta que se modifique expresamente.

Tipo de datos: si se quiere imitar un array de un tipo concreto, restringir los elementos a ese tipo mediante genéricos, por ejemplo, ArraySimulado<Integer>.

Reglas que se podrían relajar en un array utilizando una lista:
Implementación interna: permitir que la estructura utilice una lista en lugar de un array tradicional para almacenar los elementos.

Gestión de memoria: dejar que ArrayList gestione internamente su capacidad y su almacenamiento.

Inicialización: permitir que las posiciones contengan null, como sucede con las referencias de un array de objetos en Java.

Operaciones internas: permitir que la lista utilice sus propios mecanismos de almacenamiento, aunque estas operaciones no estén disponibles desde la interfaz pública del array simulado.

Qué se gana o que se pierde en un array utilizando una lista: 
Se gana: flexibilidad en la implementación, reutilización de métodos de la lista y práctica de encapsulación y abstracción.

Se pierde: simplicidad respecto al array tradicional, se añade una capa de código y puede haber un mayor consumo de memoria.





