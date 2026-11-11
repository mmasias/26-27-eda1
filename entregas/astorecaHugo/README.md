# Enmienda Técnica sobre el Diseño de `Pila` (Parte 2)

- **Alumno:** Hugo Astoreca Liaño
- **Fecha:** 11 de noviembre de 2026
- **Objeto:** Explicación de las modificaciones técnicas necesarias para adaptar el diseño de la Parte 1 a una implementación válida en Java.

---

## Fidelidad al diseño original de la Parte 1

El código desarrollado en `Pila.java` parte de la propuesta realizada en la Parte 1, conservando la idea de utilizar una cola para representar y gestionar el funcionamiento de una pila.

Se mantienen los siguientes aspectos del diseño inicial:

- **Atributos de la clase:** Se conservan los tres atributos planteados inicialmente: `Cola cola`, `int ultimo` e `int posicionEnCola`, respetando los tipos permitidos en el examen.
- **Estructura utilizada:** Se sigue trabajando exclusivamente con la clase `Cola` proporcionada y variables de tipo `int`, sin recurrir a arrays, nodos ni otras estructuras de datos.
- **Funcionamiento de las operaciones:** Se mantiene la intención original de utilizar las operaciones de la cola para introducir y extraer elementos, actualizando el valor de `ultimo` cuando cambia el elemento situado en la parte superior de la pila.
- **Métodos solicitados:** Se conservan las operaciones `apilar(int valor)`, `desapilar()` y `estaVacia()`, junto con el constructor exigido en el examen.

## Ajustes técnicos realizados para adaptar el diseño a Java

Al trasladar el diseño inicial al código aparecieron algunos aspectos que necesitaban una adaptación para cumplir las condiciones del lenguaje Java y las limitaciones de la clase `Cola`.

| Aspecto del diseño | Problema encontrado | Modificación realizada |
|---|---|---|
| **Uso de `null` en `ultimo`** | En el planteamiento inicial se utilizaba `null` para representar que la pila no tenía elementos. Sin embargo, el atributo `ultimo` está declarado como `int` y este tipo de dato no admite valores nulos. | Se sustituye esa comprobación por el método `cola.estaVacia()`. El atributo `ultimo` se inicializa con el valor 0 cuando la pila está vacía. |
| **Actualización de `ultimo` al apilar** | Guardar únicamente el último valor introducido no garantiza que se mantenga correctamente el elemento superior de la pila. | Se actualiza `ultimo` con el nuevo valor y se utilizan las operaciones de la cola para mantener el orden necesario. |
| **Extracción del último elemento** | La cola proporcionada únicamente permite extraer elementos desde el principio, por lo que no se puede acceder directamente al último elemento almacenado. | Se concreta el recorrido de la cola mediante sus operaciones públicas, utilizando las rotaciones necesarias para localizar y extraer el elemento superior. |
| **Actualización de `ultimo` al desapilar** | Después de extraer un elemento es necesario conocer cuál pasa a ser el nuevo elemento superior. | Se actualiza el atributo `ultimo` con el siguiente valor que corresponde a la parte superior de la pila. |
| **Uso de `posicionEnCola`** | En el diseño inicial se mencionaba este atributo, pero no quedaba completamente definido cómo utilizarlo en las operaciones. | Se utiliza para controlar el número de elementos y las operaciones de recorrido de la cola. |
