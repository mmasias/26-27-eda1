# Reto 002 - Nodo dummy | Lista Enlazada 
# Katerine Rafael Bourdierd

## Descripción

Implementación de la estructura `ListaEnlazada` orientada al purgado total de elementos duplicados sobre listas ordenadas ascendentemente. A diferencia de una eliminación convencional, si un valor aparece más de una vez, **se eliminan todas sus ocurrencias** sin conservar copias.

---

## Métodos

* `eliminarRepetidos()`: Elimina todos los duplicados utilizando la técnica de **nodo dummy**.
* `eliminarRepetidosSinDummy()`: Elimina todos los duplicados gestionando directamente el puntero `cabeza` sin nodos auxiliares.
* `insertarAlPrincipio(int dato)`: Inserta un nodo al inicio de la lista.
* `eliminarAlPrincipio()`: Elimina el primer nodo.
* `imprimirLista()`: Imprime la lista en formato `dato -> ... -> null`.

---