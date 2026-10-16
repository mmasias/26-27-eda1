# Katerine Rafael Bourdierd

## Estructura de Vistas y Justificación

### Lista Enlazada

El uso de una lista enlazada es ideal para este proyecto porque permite registrar a cada participante y tipo de voto como una estructura de datos dinámica y eficiente. 

En este caso, la lista maneja las siguientes 5 categorías principales:
* **Candidato 1**
* **Candidato 2**
* **Candidato 3**
* **Voto Nulo**
* **Abstención**

---

### Estructura de Clases

La solución se compone de dos clases principales:

#### 1. Clase `Nodo`
Representa cada elemento o categoría de votación en la lista.
* **Atributos:**
  * `Nombre`: Identificador de la opción o candidato.
  * `Votos`: Número acumulado de votos recibidos.
  * `Siguiente`: Puntero o referencia hacia el próximo nodo.

#### 2. Clase `ListaEnlazada`
Administra el conjunto de nodos y la lógica de manipulación de los datos.
* **Métodos principales:**
  * `Agregar`: Permite insertar una nueva categoría de votación o candidato a la lista.
  * `SumarVoto`: Busca un candidato u opción por su nombre e incrementa su contador de votos.
  * `MostrarCandidato`: Consulta y despliega la información individual de un candidato en específico.
  * `MostrarResultados`: Recorre la lista de principio a fin mostrando el desglose de votos por categoría y el total general.

---