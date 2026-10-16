# Votacion de delegado en clase

## Recogida de votos (Lista)

- Se usa una lista porque es dinámica.
- No sabes cuántos votos van a entrar en la urna, así que necesitas una estructura que pueda ir "creciendo" a medida que la gente mete su papel.

## Recuento de votos (Array)

- Se usa un array porque es estático y eficiente.
- Ya sabes que solo hay 5 opciones posibles (Candidato 1, 2, 3, Blanco y Nulo).
- Creas un array de 5 huecos fijos y simplemente vas sumando puntos en su casilla correspondiente.