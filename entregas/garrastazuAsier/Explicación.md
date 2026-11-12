# Cambios entre mi diseño inicial y la implementación final

## 1. Idea inicial

Mi idea inicial era representar la cola mediante una cadena de pilas, creando una pila independiente para cada elemento que entrase.

Cada pila almacenaría un único valor y estaría enlazada con la siguiente mediante un setter. Mantendría dos referencias: una a la primera pila y otra a la última.

De esta manera, al desencolar, bastaría con eliminar la primera pila y hacer que la siguiente pasase a ser la nueva primera.

**Considero que esta idea era válida como planteamiento**, ya que permitía representar correctamente el funcionamiento de una cola y mantener el orden de entrada y salida de los elementos.

## 2. Por qué no pude implementarla

El problema no estaba en la lógica de mi diseño, sino en las restricciones del enunciado.

La clase `Pila` viene compilada y no se puede modificar. Aunque internamente utiliza nodos enlazados, estos son privados y desde `Cola` no puedo acceder a ellos.

Únicamente puedo utilizar los métodos públicos `apilar()`, `desapilar()` y `estaVacia()`.

Por tanto, no puedo añadir un atributo ni un setter para enlazar unas pilas con otras. Esto hace que mi diseño original no sea posible de implementar con las herramientas que proporciona el examen.

## 3. Cambios realizados

Para adaptarme a estas restricciones, tuve que sustituir la cadena de pilas por dos pilas: `primera` y `ultima`.

- **Encolar:** introduzco directamente los nuevos valores en `ultima`.
- **Desencolar:** cuando `primera` está vacía, transfiero todos los elementos de `ultima` a `primera`. Así invierto el orden y puedo extraer primero el elemento más antiguo.
- **Esta vacía:** compruebo que ambas pilas estén vacías.

## 4. Conclusión

La principal diferencia entre mi diseño inicial y la implementación final es la forma de organizar los elementos.

Mi idea original era válida desde el punto de vista lógico, pero necesitaba poder enlazar objetos `Pila` entre sí, algo que no permite la interfaz pública proporcionada.

Por este motivo, tuve que adaptar la implementación utilizando dos pilas que almacenan varios elementos, manteniendo el objetivo original de construir una cola utilizando únicamente pilas.

La solución final respeta todas las restricciones del enunciado.