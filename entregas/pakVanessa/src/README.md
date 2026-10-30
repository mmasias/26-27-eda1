# Resumen – Referencias y listas enlazadas

## Ejercicio 1: Análisis de implementaciones

### a) ¿Qué implementación utiliza mejor la memoria?

La **implementación B**, porque guarda directamente la referencia al objeto Persona y no crea copias adicionales. Por tanto, consume menos memoria.

### b) Ventajas e inconvenientes

- **Implementación A:**
  - Ventaja: Los datos son independientes y no cambian al modificar el objeto original.
  - Inconveniente: Consume más memoria porque crea copias.

- **Implementación B:**
  - Ventaja: Ahorra memoria porque comparte el mismo objeto.
  - Inconveniente: Los cambios realizados en el objeto original también afectan a la lista.

### c) ¿Cómo afecta a la lista?

- **A:** Guarda copias independientes y protege los datos de cambios externos.
- **B:** Guarda referencias compartidas, por lo que cualquier modificación se refleja en la lista.

---

## Ejercicio 2: Gestión de referencias

### a) ¿Qué nombre tendrá la persona almacenada?

Tendrá el nombre **Juan Manuel**, porque `obtenerPersona()` devuelve la referencia al objeto de la lista. Al modificarlo, también se modifica el objeto almacenado.

### b) ¿Qué pasa si cambiamos Juan por Juan Carlos?

- **Implementación A:** Seguirá siendo Juan, porque el nodo guarda una copia independiente.
- **Implementación B:** Cambiará a Juan Carlos, porque ambas referencias apuntan al mismo objeto.

---

## Ejercicio 3: Referencias y constructores

### a) ¿Cuántos objetos Persona hay?

Inicialmente existen **2 objetos diferentes**:

- `manuel` y `referencia` apuntan al mismo objeto.
- `copia` apunta a un objeto independiente creado mediante el constructor copia.

Según la implementación:

- **A:** Habrá 5 objetos en total, porque se crean 3 copias adicionales.
- **B:** Seguirán existiendo 2 objetos.

### b) ¿Qué ocurre al cambiar Manuel por Manuel Antonio?

- **Implementación A:** Ningún elemento de la lista cambia, porque cada nodo tiene su propia copia.
- **Implementación B:** Cambian los elementos insertados mediante `manuel` y `referencia`, pero no el insertado mediante `copia`.

---

## Ejercicio 4: Diseño y optimización

Se proponen tres mejoras:

1. **Evitar duplicados:** Utilizar `contieneDni()` para comprobar si ya existe una persona con el mismo DNI.
2. **Elegir entre copia y referencia:** Añadir el parámetro booleano `clonar`:
   - `true`: crea una copia.
   - `false`: guarda la referencia original.
3. **Optimizar la lista:**
   - Insertar al principio tiene un coste de **O(1)**.
   - Comprobar si existe un DNI tiene un coste de **O(n)**, porque recorre la lista.

**Funcionamiento:** Primero comprueba que la persona no sea nula, después verifica que no exista su DNI y finalmente la inserta como copia o referencia según el valor de `clonar`.

---

## Ejercicio 5: Análisis de código

### a) ¿Qué problemas tiene el código?

1. Los cambios externos pueden modificar los datos de la lista.
2. `buscarPersona()` devuelve el objeto original y permite modificarlo desde fuera.
3. La comprobación de duplicados podría no detectar personas diferentes con el mismo DNI.

### b) ¿Cómo evitar modificaciones no deseadas?

Utilizando **copias defensivas**:

- Crear una copia al insertar.
- Devolver otra copia al buscar.

Así evitamos modificaciones externas sobre los datos originales de la lista.

### c) ¿Qué solución protege los datos?

- Comprobar que la persona no sea nula.
- Insertar una copia independiente.
- Devolver una copia en las búsquedas.
- Inicializar la lista en el constructor.

De esta manera, los objetos originales no pueden modificar directamente los datos almacenados.

---

## Conceptos importantes para estudiar

| Concepto | Significado |
|---|---|
| Referencia | Apunta a un objeto existente. |
| Copia | Crea un objeto independiente. |
| Implementación A | Guarda copias. |
| Implementación B | Guarda referencias. |
| Copia defensiva | Protege los datos creando copias al insertar y buscar. |
| O(1) | Coste constante. |
| O(n) | Coste proporcional al número de elementos. |

**Idea principal:** La implementación A prioriza la independencia de los datos, mientras que la implementación B prioriza el ahorro de memoria.