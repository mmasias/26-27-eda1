# Propuesta de implementación de la votación

## 1. Declaración de los arrays

Para afrontar el desarrollo del sistema de votación, utilizaría dos arrays paralelos que permitan almacenar y gestionar la información necesaria para realizar el recuento.

- **Array de candidatos (`String`):** almacenaría los nombres de los candidatos, junto con las etiquetas correspondientes a las abstenciones y los votos nulos.
- **Array de votos (`int`):** almacenaría la cantidad de votos obtenidos por cada candidato, así como el número de abstenciones y votos nulos.

Ambos arrays tendrían un tamaño igual al número de candidatos más dos posiciones adicionales.

| Array | Tipo de dato | Finalidad |
|---|---|---|
| `candidatos` | `String` | Almacenar los nombres de los candidatos y las categorías adicionales. |
| `votos` | `int` | Contabilizar los votos correspondientes a cada posición. |

## 2. Inicialización de las estructuras

Una vez declarados los arrays, introduciría los nombres de los candidatos en las primeras posiciones. Las dos últimas posiciones se reservarían para las abstenciones y los votos nulos.

Posteriormente, inicializaría todas las posiciones del array de votos a cero, ya que al comienzo de la votación todavía no se habría contabilizado ningún voto.

Por ejemplo, para tres candidatos, la distribución sería la siguiente:

| Índice | Array `candidatos` | Array `votos` |
|:---:|---|:---:|
| 0 | Candidato 1 | 0 |
| 1 | Candidato 2 | 0 |
| 2 | Candidato 3 | 0 |
| 3 | Abstenciones | 0 |
| 4 | Votos nulos | 0 |

## 3. Registro y contabilización de los votos

A continuación, implementaría el proceso de recogida de votos. Para ello, comprobaría a qué candidato o categoría corresponde cada voto y aumentaría en una unidad el contador de la posición correspondiente del array `votos`.

El procedimiento se repetiría hasta que finalizase la votación.

```text
       ┌───────────────────┐
       │    Recibir voto   │
       └─────────┬─────────┘
                 │
                 ▼
       ┌───────────────────┐
       │ Identificar el    │
       │ tipo de voto      │
       └─────────┬─────────┘
                 │
                 ▼
       ┌───────────────────┐
       │ Incrementar la    │
       │ posición adecuada │
       └─────────┬─────────┘
                 │
                 ▼
       ┌───────────────────┐
       │ ¿Quedan votos     │
       │ por registrar?    │
       └─────────┬─────────┘
                 │
          Sí ────┴──── No
           │           │
           ▼           ▼
      Recibir otro   Mostrar los
         voto        resultados