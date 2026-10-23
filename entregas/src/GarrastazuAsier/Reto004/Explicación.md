| **Dato** | **Decisión** | **Justificación** | **Alternativa descartada** | **Limitación asumida** |
|---|---|---|---|---|
| **Censo / Alumnos presentes** | Lista enlazada dinámica propia (`Nodo`) | Permite añadir y eliminar alumnos durante la ejecución del programa. | Array de tamaño fijo | Buscar un alumno requiere recorrer la lista. |
| **Papeletas depositadas (Urna)** | Cola (`Queue`) | Sigue el principio FIFO: la primera papeleta en entrar es la primera en salir. | Pila (`Stack`) | No permite acceder directamente a una papeleta del interior. |
| **Historial de acciones** | Pila (`Stack`) | Permite recuperar la última acción registrada para deshacerla. | Cola (`Queue`) | Para deshacer una acción antigua, hay que gestionar las acciones posteriores. |
| **Candidaturas activas** | Lista dinámica (`ArrayList`) | Permite añadir, consultar y retirar candidaturas durante la preparación de las elecciones. | Array de tamaño fijo | Buscar una candidatura requiere recorrer la lista. |
| **Recuento de votos** | Array de enteros (`int[]`) | Guarda el número de votos de cada candidatura mediante una posición asociada a cada candidato. | Variables independientes para cada candidato | Si cambian las candidaturas, hay que adaptar el array y mantener correctamente la correspondencia entre posiciones y candidatos. |
| **Control de participación** | Lista de identificadores (`ArrayList<String>`) | Permite comprobar si un alumno ya ha votado antes de aceptar otro voto. | Variables independientes | Hay que recorrer la lista para comprobar si un alumno ya ha votado. |
| **Identificación de alumnos** | Búsqueda secuencial en la lista enlazada | Permite comprobar si un alumno pertenece al censo utilizando los nodos. | Estructuras asociativas | La búsqueda puede ser lenta si el censo tiene muchos alumnos. |
| **Estado de las elecciones** | Variable `boolean` | Indica si la votación está abierta (`true`) o cerrada (`false`). | Enumeración (`enum`) | Solo representa dos estados; no permite distinguir directamente entre preparación, votación y escrutinio. |
| **Resultados finales** | Clase `Resultado` | Agrupa los votos obtenidos por cada candidatura y los votos en blanco y nulos. | Variables independientes | Requiere crear una clase adicional. |
| **Almacenamiento de datos** | Archivos, si se han estudiado en clase | Permite guardar los resultados para consultarlos después de cerrar el programa. | Mantener los datos solo en memoria | Sin almacenamiento permanente, los datos se pierden al cerrar el programa. |

## Clases que se deben crear

| **Clase** | **Función principal** |
|---|---|
| `Alumno` | Guarda los datos de cada alumno. |
| `Nodo` | Guarda un alumno y la referencia al siguiente nodo. |
| `ListaAlumnos` | Gestiona la lista enlazada dinámica del censo. |
| `Candidato` | Guarda los datos de cada candidato. |
| `Voto` | Representa una papeleta electoral. |
| `Eleccion` | Gestiona el proceso electoral. |
| `Recuento` | Contabiliza los votos. |
| `Historial` | Registra las acciones que se pueden deshacer. |
| `Principal` | Contiene el método `main` y el menú del programa. |
