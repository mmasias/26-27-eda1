# Diseño de Cola con una sola Pila

**Atributos de la clase Cola:**
* `Pila` (Instancia de la clase Pila)

---

## 1. Operación: `encolar(int valor)`

**Acción:** Traspasa el mensaje. Llama directamente a `Pila.apilar(valor)`.

```text
Ejemplo: encolar(3)
    
   [ Pila ]
     | 3 |  <-- Entra en la cima
     | 2 |
     |_1_|
```

---

## 2. Operación: `estaVacia()`

**Acción:** Traspasa el mensaje. Llama directamente a `Pila.estaVacia()`.

---

## 3. Operación: `desencolar()`

**Acción:** Utiliza una variable local `PilaVolteada` para invertir la pila original, extraer el elemento más antiguo, y luego restaurar la pila original.

### Paso A: Volcar todo a la pila local
Se desapila todo de `Pila` y se apila en `PilaVolteada`.

```text
    [ Pila ]               [ PilaVolteada ] (Variable Local)
      | 3 |                      | 1 |
      | 2 |       ======>        | 2 |
      |_1_|       traspaso       |_3_|
```

### Paso B: Extraer el primer elemento que entró
Se desapila el tope de `PilaVolteada`. Este es el valor que retornará la función.

```text
                           [ PilaVolteada ]
                                 |   |  ---> Sale el 1 (Se guarda para retornar)
                                 | 2 |
                                 |_3_|
```

### Paso C: Regresar el resto a la original
Se desapila todo de `PilaVolteada` y se vuelve a apilar en `Pila`.

```text
 [ PilaVolteada ]               [ Pila ]
      |   |                      |   | 
      | 2 |       ======>        | 3 |
      |_3_|       traspaso       |_2_|
```