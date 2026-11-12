# Registro de desviaciones respecto al diseño congelado (Parte 1)

El diseño de la Parte 1 es viable y se mantiene fiel en su estructura central: se utiliza un único atributo `Pila` (`principal`) donde los elementos se mantienen ordenados en orden FIFO (el más antiguo en la cima y el más reciente en el fondo), resolviendo `desencolar()` mediante extracción directa (`desapilar()`) y realizando la reordenación en `encolar(int valor)`.

Se registra únicamente la siguiente desviación menor de implementación:

1. **Unificación del doble vuelco en `encolar(int valor)` (eliminación del caso especial para 1 elemento):**
   * **Diseño original (Punto 3):** En `encolar(2)` (cuando solo había 1 elemento en la pila) se planteó mover ese único elemento directamente a la nueva pila, mientras que a partir de `encolar(3)` se introdujo una pila auxiliar intermedia para invertir dos veces y conservar el orden FIFO.
   * **Código entregado:** Siempre que `!principal.estaVacia()`, el método `encolar` utiliza la pila auxiliar intermedia (`principal` $\rightarrow$ `auxiliar` $\rightarrow$ `nueva`).
   * **Justificación:** La vista pública de `Pila` provista (`apilar`, `desapilar`, `estaVacia`) no dispone de un método para consultar el número de elementos sin desapilarlos. Por tanto, no es posible distinguir si `principal` tiene 1 o más elementos antes de extraerlos. Aplicar siempre el doble vuelco produce exactamente el mismo estado resultante cuando hay 1 elemento (`encolar(2)`) y garantiza el orden FIFO correcto para $N \ge 2$ elementos (corrigiendo además la ambigüedad de redacción del Punto 1).


> [!NOTE]
> **Aclaración terminológica:** En la redacción de la Parte 1 se emplearon los términos *"nodos"* y *"objetos"* de forma genérica para referirse a cada elemento almacenado en la estructura, no a instancias de una clase `Nodo` propia ni a objetos por referencia, respetando así la restricción de trabajar únicamente con `Pila` y valores `int`.