# PROPUESTA ELECCIONES

## Array de candidatos (String)

- Almacena los nombres de los alumnos que se presentan a las elecciones de delegado.
- Cada posición representa a un candidato.
- Tamaño de inicialización: número de candidatos.

## Array de votos (Int)

- Almacena el número de votos obtenidos por cada candidato.
- Cada posición se corresponde con el candidato situado en la misma posición del array de candidatos.
- Tamaño de inicialización: número de candidatos + 2, para incluir los votos nulos y las abstenciones.

## Pila de votos (Stack)

- Almacena temporalmente los votos emitidos durante las elecciones.
- Sigue el principio LIFO (Last In, First Out): el último voto en entrar es el primero en salir.
- Permite procesar los votos durante el recuento hasta que la pila quede vacía.

## Algoritmo de recuento de votos

- Extrae los votos de la pila uno a uno hasta que quede vacía.
- Identifica a qué candidato corresponde cada voto.
- Incrementa el contador de votos del candidato correspondiente.
- Si el voto es nulo o corresponde a una abstención, incrementa el contador correspondiente.

## Algoritmo de búsqueda del ganador

- Recorre el array de votos para comparar los resultados de los candidatos.
- Identifica al candidato que ha obtenido el mayor número de votos.
- Si varios candidatos obtienen el mismo número máximo de votos, identifica el empate para aplicar el procedimiento establecido.