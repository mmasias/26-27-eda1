# Registro de desviaciones respecto al diseño congelado (Parte 1)

El diseño planteado en la Parte 1 es viable y se ha implementado respetando fielmente la estrategia central de utilizar dos pilas (`pila` como pila principal de almacenamiento y `pilaAuxiliar` como pila auxiliar de trasvase) sin recurrir a estructuras de datos adicionales ni recursión.

No obstante, al trasladar el diseño en papel a la implementación en `Cola.java`, se registran las siguientes concreciones y ajustes menores:

1. **Retorno de elementos desde `pilaAuxiliar` a `pila` en `desencolar()`:**
   * **Diferencia:** En los diagramas de la Parte 1 se representó el volcado de los elementos desde la pila principal (`pila`) hacia la auxiliar (`pilaAuxiliar`) para extraer el elemento más antiguo, pero no se detalló explícitamente el volcado inverso posterior. En `Cola.java`, tras desapilar el elemento de la cima de `pilaAuxiliar`, se devuelven todos los elementos restantes a `pila`.
   * **Justificación:** Permite mantener el invariante real de la estructura tras cada operación pública: `pilaAuxiliar` queda siempre vacía al finalizar y todos los elementos de la cola permanecen ordenados en `pila`, posibilitando que `encolar()` apile directamente en `pila` en tiempo constante.

2. **Nomenclatura de los atributos:**
   * **Diferencia:** En los bocetos en papel se abreviaron las pilas como `P` y `PAux`, mientras que en `Cola.java` se han nombrado `pila` y `pilaAuxiliar`.
   * **Justificación:** Mejora la legibilidad y claridad del código manteniendo exactamente la misma correspondencia de roles definida en el diseño.

3. **Especificación explícita de `estaVacia()` y precondición de `desencolar()`:**
   * **Diferencia:** En el documento escrito no se detalló el funcionamiento interno de `estaVacia()` ni la verificación explícita de la precondición de `desencolar()`. En el código, `estaVacia()` delega directamente en `pila.estaVacia()` y `desencolar()` incorpora `assert !estaVacia()`.
   * **Justificación:** Cumple con el contrato exigido en el enunciado (`assert` en lugar de `if` o excepciones) y aprovecha que, al quedar `pilaAuxiliar` vacía tras cada operación, el estado vacío de `pila` determina por completo el estado vacío de la cola.

4. **Aclaración en la traza de `encolar(4)` respecto a los diagramas:**
   * **Diferencia:** En el diagrama de memoria de la Parte 1 se anotó *"igual que (3)"* en el paso `encolar(4)` para indicar que la mecánica de inserción es idéntica (apilar en la cima de `pila`). En las pruebas (`Cliente.java`), se verifica el estado real de la cola tras dicha operación (`[2, 3, 4]`, dado que el `1` ya había sido desencolado).
   * **Justificación:** Aclara la notación abreviada empleada en el papel y demuestra mediante aserciones que las siguientes llamadas a `desencolar()` devuelven `2` y `3` tal como exige el comportamiento FIFO.