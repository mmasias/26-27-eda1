# Errores y cosas que debo mejorar

Al revisar mi primer código me he dado cuenta de que tenía bastantes errores, tanto de sintaxis como a la hora de plantear el ejercicio.

El principal problema es que no tenía claro cómo construir una cola utilizando pilas. Empecé declarando la clase y sus métodos, pero realmente no llegué a implementar su funcionamiento.

Estos son los errores que he encontrado:

- Declaré dos veces la clase `Cola`, cuando solamente necesitaba una.
- Puse un punto y coma después del constructor (`public Cola();`), cuando tenía que abrir y cerrar las llaves para desarrollar su contenido.
- Confundí el constructor de `Cola` con el de `Pila`.
- No implementé los métodos `encolar()`, `desencolar()` y `estaVacia()`, simplemente los dejé declarados.
- No creé los atributos necesarios para trabajar con las pilas.
- No tenía claro cómo llamar a los métodos de un objeto `Pila`. Utilicé una sintaxis incorrecta al escribir cosas como `Pila.encolar()`.
- No entendía cómo invertir el orden de los elementos para conseguir que la cola funcionase correctamente.
- No utilicé `assert` para comprobar la condición necesaria antes de desencolar un elemento.

## Cosas que debo mejorar

Después de revisar el ejercicio, creo que debo mejorar principalmente estos aspectos:

- Entender mejor cómo se crean los objetos y cómo se utilizan sus métodos.
- Practicar la sintaxis de Java, especialmente los constructores y la implementación de métodos.
- Aprender a plantear mejor los ejercicios antes de empezar a programar.
- Comprender mejor las diferencias entre una pila y una cola.
- Practicar cómo utilizar unas estructuras de datos para implementar otras.
- Leer con más atención las condiciones y restricciones del enunciado antes de empezar.

En general, me he dado cuenta de que mi mayor dificultad fue no saber cómo plantear la solución utilizando las dos pilas. Ahora entiendo mejor cómo funciona el intercambio de elementos entre ellas y por qué es necesario para mantener el orden de una cola.
