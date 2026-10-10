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





Reglas que habría que imponer en lista utilizando un array:
Tamaño lógico variable: el número de elementos puede aumentar o disminuir, aunque el array tenga una capacidad determinada.

Orden: al insertar o eliminar elementos, hay que desplazar los demás para mantener su posición relativa.

Índices válidos: solo se puede acceder a posiciones que contengan elementos; al insertar, también se permite el índice tamaño.

Redimensionamiento: cuando el array se llena, debemos crear otro más grande y copiar los elementos.


Reglas que habría que relajar en lista utilizando un array:
Capacidad fija del array: aunque un array no puede cambiar de longitud, podemos sustituirlo por otro de mayor tamaño.

Tamaño físico igual al número de elementos: el array puede reservar más posiciones de las que utiliza la lista.

Necesidad de implementar todas las operaciones directamente: podemos construir métodos como añadir, insertar y eliminar para proporcionar una interfaz de lista sobre el array.




¿Qué se gana y que se pierde?
Se gana: una lista que puede crecer, reducirse, insertar elementos y eliminarlos utilizando únicamente un array como almacenamiento.

Se pierde: simplicidad, porque hay que controlar manualmente el tamaño, los desplazamientos y las ampliaciones.



