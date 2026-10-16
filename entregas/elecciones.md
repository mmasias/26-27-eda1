## Array de votos (Int)
- Almacena los votos de cada candidato.
- Almacena además los votos nulos y las abstenciones.
- Tamaño de inicialización: número de candidatos + 2.
## Array de candidatos (String)
- Almacena los nombres de todos los candidatos a delegado.
- Cada posición se corresponde con la misma posición en el array de votos.
- Tamaño de inicialización: número de candidatos.
## Pila de votos (Stack)
- Representa los votos que el profesor va recogiendo y apilando.
- Sigue el principio LIFO (Last In, First Out): el último voto en entrar es el primero en salir.
- Permite almacenar temporalmente los votos hasta que se realice el recuento.
## Algoritmo de búsqueda del ganador
- Compara el número de votos obtenidos por cada candidato.
- Identifica al candidato que ha conseguido más votos.
- En caso de empate, se aplica el procedimiento establecido para resolverlo.