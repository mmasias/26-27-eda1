# Registro de Desviaciones / Enmienda Declarada

### Qué falla
En el diseño original que entregué en papel, propuse utilizar una única estructura de tipo `Pila` para simular el comportamiento de la `Cola`.

### Cómo lo comprobé
Durante la implementación del método `desencolar()` en el ordenador, me di cuenta de un problema lógico: al ser la pila una estructura LIFO, el primer elemento ingresado queda atrapado en el fondo. Como las reglas del examen prohíben estrictamente el uso de recursión o de estructuras de datos auxiliares, resulta imposible sacar ese primer elemento (para lograr el comportamiento FIFO) sin perder los que están apilados encima. Mi diseño inicial es inviable bajo estas restricciones

### Qué cambia
Para solucionarlo, modifico mi estrategia central y paso a utilizar **dos atributos de tipo `Pila`**, respetando al pie de la letra la restricción del examen que indica que los atributos solo pueden ser de tipo `Pila` o `int`.

1. **Nueva estrategia:** 
   * Utilizaré una pila llamada `entrada` para procesar el método `encolar()`.
   * Utilizaré una segunda pila llamada `salida` para procesar el método `desencolar()`. 
   * Cuando se llame a `desencolar()`, si la pila `salida` está vacía, volcaré todos los elementos de la pila `entrada` hacia la de `salida` (haciendo un `apilar(desapilar())`). Este trasvase invierte naturalmente el orden de los elementos, dejando el más antiguo exactamente en el tope y listo para ser extraído

2. **Invariante actualizado:** 
   * El próximo elemento a desencolar se encuentra siempre en el tope de la pila de `salida`; si esta se encuentra vacía, dicho elemento se encuentra en el fondo de la pila de `entrada`