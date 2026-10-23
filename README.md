# RETO 004

- Katerine Lisbeth Rafael Bourdierd

# Reto 004 - Refactoring de la Propuesta Electoral

Este repositorio contiene la solución completa para el proceso electoral utilizando una arquitectura basada dinámicamente en **Java Plain Vanilla**, prescindiendo de los paquetes estándar de `java.util`.

---

## 1. Propuesta de Arquitectura y Decisiones de Estructuras

### Tabla de Decisiones

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
| :--- | :--- | :--- | :--- | :--- |
| **Categorías y Candidaturas de Votación** | `ListaEnlazada` (Clase `ListaEnlazada` con objetos `Nodo`) | Permite registrar dinámicamente cada categoría ("Candidato 1", "Candidato 2", "Candidato 3", "Voto Nulo", "Abstención") y actualizar sus conteos acumulados sin depender de índices fijos. | `Array` de tamaño estático para candidatos, pues no permite insertar o reorganizar categorías dinámicamente ante bajas o incorporaciones de última hora. | Búsqueda secuencial O(N) para localizar el nodo de un candidato u opción al ejecutar `sumarVoto`. |
| **Control de Asistencia de Alumnos** | `ListaEnlazada` de Votantes/Asistentes | Permite gestionar la llegada tardía e inserción de alumnos, así como la eliminación de un alumno que abandona la sala antes de ejercer su voto. | `Cola` de asistencia, pues solo admitiría salidas en orden FIFO e impediría que un alumno intermedio abandone el aula voluntariamente. | El borrado de un alumno que se retira de la sala requiere un recorrido de nodos de coste O(N). |
| **Urna (Recepción de Papeletas)** | `Cola` | Mantiene el orden estricto de llegada cronológica de cada papeleta depositada para su posterior procesamiento secuencial en el escrutinio. | `Pila`, ya que invertiría el orden de extracción (LIFO), escrutando en primer lugar los últimos votos depositados en lugar de los primeros. | No es posible inspeccionar ni corregir un voto dentro de la urna sin extraer previamente todos los votos colocados por encima de él. |
| **Historial de Modificaciones del Recuento** | `Pila` | Facilita la reversión inmediata de la última anotación procesada en la lista de candidaturas en caso de error de lectura durante el escrutinio (mecanismo *Undo/Deshacer*). | `Cola`, porque eliminaría la primera anotación efectuada en lugar de la última realizada, distorsionando el conteo de la mesa. | Solo permite corregir errores de forma inmediata sobre la última anotación acumulada en el nodo correspondiente. |

---

### Cambios respecto a la propuesta previa

#### Decisiones Mantenidas
* **Uso de `Cola` para la Urna**: Se mantiene para asegurar la fidelidad cronológica de extracción de votos tal y como fueron depositados.
* **Uso de `Pila` para el Historial del Recuento**: Se mantiene para revertir o corregir en la `ListaEnlazada` el último nodo incrementado por error durante el escrutinio.

#### Decisiones Cambiadas
* **Estructuración del Conteo en Nodos (`ListaEnlazada` de Categorías)**: Se adopta el modelo donde cada candidato u opción de voto es un `Nodo` individual (`nombre`, `votos`, `siguiente`) dentro de la `ListaEnlazada`. Esto simplifica la gestión del escrutinio mediante métodos directos como `sumarVoto(nombre)` y la visualización final con `mostrarResultados()`.
* **Inclusión explícita de "Abstención" como Categoría de la Lista**: Anteriormente la abstención se calculaba únicamente como un valor derivado fuera de la estructura de conteo. Ahora se integra como un nodo de la lista para registrar cuantitativamente la falta de participación presencial junto a los votos nulos y candidaturas.

---

## 2. Retos Extendidos

### Reporte de Abstención al Cierre
* **Fórmula de Cálculo**: Abstención Registrada = Alumnos Presentes sin Votar = Nodos de Asistencia - Nodos de Votantes Efectivos.
* **Estructuras consultadas**: Se obtiene consultando la cantidad de alumnos en la `ListaEnlazada` de Asistencia y comparándola con la `ListaEnlazada` de Votantes Efectivos. Posteriormente, dicho total se almacena e incrementa en el nodo `"Abstención"` de la `ListaEnlazada` de resultados.
* **Por qué no se puede contar leyendo papeletas**: Las papeletas leídas de la urna corresponden únicamente a las interacciones con el recipiente de votación (votos a candidatos, en blanco o nulos). Un alumno que asistió al aula pero decidió marcharse o rehusó depositar un sobre jamás generará una papeleta física en la urna.

### Debate y Refutación de la Alternativa de Abordaje
* **Decisión rebatida**: Utilizar un `Array` estático `votos[index]` donde la posición indexada rígida representa a cada candidato.
* **Hecho del enunciado no atendido**: El enunciado contempla candidaturas retiradas o rechazadas antes o durante la jornada electoral.
* **Alternativa no considerada**: Representar el conteo mediante una `ListaEnlazada` dinámica donde se pueden agregar o eliminar nodos de candidaturas sin romper la integridad de los datos.
* **Limitación no declarada**: Si la candidata 2 es rechazada, la posición `votos[1]` del array queda inutilizada, exigiendo desplazamientos manuales de memoria O(N) o arriesgando lecturas sobre datos nulos/inválidos durante el escrutinio.

---

## 3. Estructura del Código Fuente

### Clase `Nodo`
Representa cada elemento o categoría de votación dentro de la lista.
* `nombre`: Identificador de la opción o candidato.
* `votos`: Contador acumulado de votos recibidos.
* `siguiente`: Referencia al próximo nodo.

### Clase `ListaEnlazada`
Administra el conjunto de nodos y aplica la lógica de manipulación de datos.
* `agregar(nombre)`: Inserta una nueva categoría o candidato.
* `sumarVoto(nombre)`: Incrementa el contador de votos tras buscar la categoría por su nombre.
* `restarVoto(nombre)`: Decrementa el contador (útil para desapilar y corregir errores).
* `mostrarCandidato(nombre)`: Consulta y despliega la información individual de un candidato específico.
* `mostrarResultados()`: Recorre la lista mostrando el desglose de votos por categoría y el total general.

---
