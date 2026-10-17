# Vanessa Pak Castillo

## Estructura de Vistas y Justificación

### Lista Enlazada
Se utiliza una lista enlazada para gestionar las cinco categorías de votación: **Candidato 1, Candidato 2, Candidato 3, Voto Nulo y Abstención**.

### Estructura de Clases

- **`Nodo`:** Contiene el nombre de la categoría, el número de votos y la referencia al siguiente nodo.
- **`ListaEnlazada`:** Administra los nodos mediante los métodos:
  - `Agregar`: Añade categorías.
  - `SumarVoto`: Registra los votos.
  - `MostrarCandidato`: Consulta los votos de un candidato.
  - `MostrarResultados`: Muestra los resultados y el total de votos.