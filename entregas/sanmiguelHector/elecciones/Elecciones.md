# Explicación del Sistema de Votación para Elección de Delegado

---

## 1. Estructura de Datos y Justificación

Para modelar el proceso electoral se han utilizado dos arrays de tamaño fijo.

### Array de Candidatos (String[])
- **Tamaño:** N.
- **Contenido:** Almacena los nombres de los candidatos en las posiciones desde $0$ hasta $N-1$.

### Array de Votos (int[])
- **Tamaño:** N + 2.
- **Contenido:**
  - Posiciones de 0 a N-1: Almacenan la cantidad acumulada de votos de cada candidato
  - Posición $N$: Reservada para los **votos nulos**.
  - Posición $N+1$: Reservada para las **abstenciones**.

## 2. Flujo de Ejecución del Algoritmo

El proceso se ejecuta en tres fases bien definidas:

```
[1. Inicialización] ──> [2. Registro de Votos] ──> [3. Escrutinio y Resultados]
```

### Fase 1: Inicialización
1. Se define N (número de candidatos).
2. Se instancian las estructuras en memoria:
   - candidatos de tamaño N.
   - votos de tamaño N + 2.
3. Se inicializan todos los elementos del array votos a 0.

### Fase 2: Registro de Votos
Por cada estudiante votante:
1. Se le presenta un menú donde cada opción equivale a un índice numérico $k$:
   - Opciones 0 a N-1: Candidatos específicos.
   - Opción N: Voto Nulo.
   - Opción N+1: Abstención.
2. Al ingresar la opción k, el algoritmo actualiza el conteo en tiempo constante:
   $$\text{votos}[k] \leftarrow \text{votos}[k] + 1$$

### Fase 3: Escrutinio y Elección del Delegado
1. **Búsqueda del Máximo:** Se itera desde el índice $0$ hasta $N-1$ sobre el array votos para determinar la posición con mayor número de sufragios.
2. **Determinación del Ganador:** El delegado electo será el Candidato[k] donde k representa el índice del máximo de votos válidos.
3. **Cálculo de Totales:**
   - Total Votos Emitidos = Sumatorio (N+1) (i = 0) de votos[i]
   - Total Votos Válidos = Sumatorio (N-1) (i = 0) de votos[i]

---

## 3. Análisis Técnico y Complejidad Algorítmica

1. **Direccionamiento Directo:** Permite procesar los votos en tiempo constante al usar el índice de la opción como clave de acceso directo.
2. **Eficiencia en Memoria:** Evita la sobrecarga de estructuras complejas, ya que el número de opciones (candidatos + nulos + abstenciones) se conoce a priori y no cambia durante la ejecución.
