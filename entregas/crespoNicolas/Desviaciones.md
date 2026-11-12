# Registro de desviaciones

**Declaración general:** 
No existen desviaciones algorítmicas ni estructurales ni ha sido necesario realizar ninguna enmienda respecto al diseño congelado en la Parte 1. La implementación en `Cola.java` sigue fielmente la estrategia central descrita en el Punto 1 y en los tres puntos de resumen entregados: uso exclusivo de dos instancias de `Pila` (`pilaOriginal` para la entrada en orden de llegada y `pilaEspejo` para invertir el orden y extraer el frente), realizando la actualización y reinversión antes de la siguiente operación.

Únicamente se dejan registradas las siguientes **precisiones de implementación** respecto a la notación empleada en el documento manuscrito:

1. **Precisión sobre el Punto 2 (Invariante de simultaneidad y actualización):**
   * **Diseño en papel:** Se indicó que existen dos estructuras de manera simultánea (la original y la invertida) y que ambas se actualizan y reinvierten antes del siguiente dato/operación.
   * **Concreción en código:** Dado que la vista pública de `Pila` solo permite extraer elementos mediante `desapilar()` (operación destructiva sobre la pila origen), ambas instancias (`pilaOriginal` y `pilaEspejo`) coexisten simultáneamente en memoria como atributos de `Cola`, pero los elementos se transfieren de una a otra sin duplicarse. Al finalizar la actualización y reinversión al término de cada operación pública, todos los elementos vigentes reposan en `pilaOriginal` en su orden de llegada y `pilaEspejo` queda vacía, lista para la siguiente operación.

2. **Precisión sobre el Punto 3 (Granularidad de la tabla de estados):**
   * **Diseño en papel:** En la tabla de seguimiento se representó una fila por cada operación del cliente indicando la acción principal (`apilar` / `desapilar`) y el estado lógico resultante de los elementos en la estructura (`[1]`, `[1, 2]`, `[1, 2, 3]`, `[2, 3]`, `[2, 3, 4]`, `[3, 4]`, `[4]`).
   * **Concreción en código:** Mientras que `encolar(valor)` se corresponde directamente con una única llamada a `pilaOriginal.apilar(valor)`, cada fila de `desencolar()` de la tabla agrupa internamente en el código el ciclo completo descrito en el texto del Punto 1:
     1. Volcado de `pilaOriginal` a `pilaEspejo` mediante llamadas sucesivas a `pilaEspejo.apilar(pilaOriginal.desapilar())`.
     2. Extracción del elemento más antiguo con `pilaEspejo.desapilar()`.
     3. Reinversión inmediata de `pilaEspejo` a `pilaOriginal` mediante `pilaOriginal.apilar(pilaEspejo.desapilar())`, dejando exactamente el estado reflejado en la columna "Estado" de la tabla manuscrita antes de la siguiente instrucción del cliente.
