# Estructuras vistas y justificación

## Array de votos (int)

- Almacena los votos de los candidatos
- Almacena además los votos nulos y las abstenciones
- Tamaño de inicialización: # candidatos + 2

## Array de candidatos (String)

- Almacena los nombres de los candidatos
- Tamaño de inicialización: # candidatos

SOLUCIÓN PLANTEADA:

La solución propuesta utiliza dos estructuras contiguas de memoria:

### Array de Candidatos (`String[]`)
* **Tipo de dato:** Cadena de caracteres (`String` / `std::string`).
* **Propósito:** Almacenar los nombres o identificadores de los candidatos participantes.
* **Tamaño de inicialización:** $N$ (donde $N$ es la cantidad de candidatos inscritos).
* **Justificación:** Los nombres representan información estática fijada al inicio del proceso. Un arreglo de tamaño fijo optimiza el uso de memoria y elimina la necesidad de reasignaciones dinámicas durante el escrutinio.

### Array de Votos (`int[]`)
* **Tipo de dato:** Entero numérico (`int` / `long`).
* **Propósito:** Registrar y acumular el conteo de votos de cada candidato, así como los registros no adjudicados a ningún postulante.
* **Tamaño de inicialización:** $N + 2$ (donde $N$ es la cantidad de candidatos).
* **Justificación:** Permite centralizar en un único contenedor lineal toda la contabilidad del proceso electoral, garantizando operaciones de incremento en tiempo constante $O(1)$.
