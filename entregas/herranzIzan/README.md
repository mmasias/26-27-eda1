# Lo sucedido en las elecciones


## Configuración Inicial (Preparando la Elección)

Antes de abrir las urnas, necesitamos definir las reglas del juego y quiénes participan utilizando estructuras de tamaño fijo.

### - Array de Strings (String[] candidatos)
Lo usamos para definir la boleta electoral de forma permanente. Contiene las opciones elegibles: {"Hector San Miguel", "Adriana Aguilar", "Katerine Rafael", "Nulo", "Abstención"}. Al ser un arreglo estático, garantizamos que nadie pueda modificar, añadir o eliminar candidatos a mitad de la jornada electoral de manera fraudulenta.

### - Lista Dinámica (List<String> padronElectoral)
Funciona como la lista de asistencia oficial de la carrera. Se usa una lista dinámica porque el número de alumnos de ingeniería informática puede cambiar en los días previos (por ejemplo, si un compañero no asiste a la votación o se da de baja de la asignatura). Contiene finalmente los nombres de los alumnos con derecho a voto, un total de 34.

## El Día de la Elección (El Flujo de Voto)

Cuando comienza la votación en el aula, el movimiento y la seguridad de los datos dependen de estructuras lineales.

### - Colas - La Fila Inexistente (Cola implícita por pasillos)

El profesor decide su ruta (pasillo derecho de adelante hacia atrás, luego pasillo izquierdo). El orden en el que el profesor llega a los asientos define la estructura de la cola de atención. El alumno del primer asiento de la derecha es el primero en entrar a la "cola" de recolección y el primero a quien se le recibe el papelito; el alumno del fondo a la izquierda será el último.

### - Pilas - La Mano del Profesor

Para recolectar los votos de forma anónima mientras camina, el profesor los va agarrando en su mano conforme pasa asiento por asiento. Esto vuelve a ser una Pila (LIFO).
• Flujo: El profesor llega al primer asiento y toma el papelito. Camina al siguiente asiento, toma otro papelito y lo pone encima del anterior.
• Anonimato y Escrutinio: Cuando el profesor termina de recorrer toda la clase y regresa a su mesa, y obtiene los 34 papelitos. Al empezar el conteo, el secretario saca el papelito que quedó hasta arriba, el cual pertenece al último alumno que visitó en su recorrido. El voto del primer alumno visitado se queda resguardado en el fondo de la pila.

### - Array (int) - El Marcador Final en la Pizarra

El profesor se para frente a la pizarra donde tiene el Array de enteros inicializado en ceros para las 5 categorías (Héctor, Adriana, Katerine, Abstención, Nulo).
• Flujo: Con la mano libre,  el secretario toma el papelito superior de la Pila, lo abre, lee el voto y el profesor incrementa en "I" el valor del índice correspondiente en el Array de la pizarra. Repite este proceso bajando por el montón hasta que procesa el voto del fondo. Al terminar, la pizarra refleja el conteo final de la clase.
