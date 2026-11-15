## Reto 005 - Uroboro vII

> Se está haciendo este trabajo de manera individual: Felix Manrique

### Indicaciones:
A partir de lo trabajado en el Examen Parcial (Pila implementada con Cola o Cola implementada con Pila), extienda el ejercicio de modo que:
- Si abordó la Pila utilizando una Cola, use a su vez una Pila en la implementación de la Cola.
- Si abordó la Cola utilizando una Pila, use a su vez una Cola en la implementación de la Pila.

### El que se va a resolver es:
- Habiendo abordado la `Cola` utilizando una `Pila` en el Examen Parcial, usaremos a su vez esa `Cola` en la implementación de una nueva `Pila` (`PilaEnCola`).

### Arquitectura de la solución:
1. **Base (`Pila.java` y `Nodo.java`):** Implementación base en memoria mediante nodos enlazados.
2. **Examen Parcial (`Cola.java`):** Cola implementada utilizando dos instancias de la `Pila` base (`pilaEntrada` y `pilaSalida`).
3. **Extensión Uroboro (`PilaEnCola.java`):** Pila implementada utilizando una instancia de `Cola` y un contador entero `elementos`, rotando los valores al apilar para mantener el orden LIFO.
4. **Pruebas (`Main.java`):** Ejecución de la secuencia de pruebas verificando el comportamiento LIFO de `PilaEnCola`.