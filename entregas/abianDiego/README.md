# Elección de delegado: ¿array, lista o cola?

## Array, para contar los votos

Los candidatos se conocen antes de votar y no cambian. A cada uno le toca una casilla y cada voto suma uno en la suya. Llegar a una casilla por su índice es inmediato.

```
votos: | Ana: 7 | Luis: 12 | Marta: 5 |
```

## Cola, para los votantes

Los alumnos votan por orden de llegada: el primero que llega es el primero que vota (FIFO).

## ¿Por qué no una lista?

Una lista sirve cuando se mete o se saca gente por el medio, y aquí eso no pasa. Además, para llegar a un candidato habría que recorrerla.
