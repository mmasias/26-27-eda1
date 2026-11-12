# Registro de Desviaciones / Enmienda Declarada

### Qué falla: 
- El diseño original estructurado en la Parte 1 utilizaba una sola instancia de Pila y una variable llamada ultimo. Durante la implementación, identifiqué que este modelo es inviable porque conserva de forma inherente el comportamiento LIFO. El primer elemento ingresado queda encapsulado en el fondo de la estructura, volviendo imposible su extracción sin comprometer los datos superiores.
- 
### Cómo lo comprobé: 
- Al codificar y ejecutar la secuencia de prueba requerida (encolar(1), encolar(2), desencolar()), mi diseño devolvía el valor 2 (comportamiento Pila) en lugar del 1 (comportamiento Cola).

### Qué cambia: 
- Para cumplir estrictamente con el contrato de una Cola (FIFO), se enmienda la arquitectura reemplazando la variable primitiva por una segunda estructura de soporte. Se utilizan dos atributos de tipo Pila, pilaEntrada y pilaSalida. Las inserciones ocurren en la entrada, y las extracciones se realizan moviendo los datos a la salida, lo cual invierte el orden y expone correctamente el elemento más antiguo.