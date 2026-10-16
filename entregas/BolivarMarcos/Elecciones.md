## Array de candidatos (String)(Nombre y apellido de candidato)
- Al ser una cantidad establecida de personas, se exactamente el número de espacios que r¡tiene que tener el array, Sin tener que añadir más o quitar nada. Inicio el array siendo del tamaño del número de candidatos postulados a la delegación añadiendo otros dos por si alguien vota nulo o en blanco.

## Array de votos, (int)(0,1)
- El array es del tamaño del número de personas que hay en la clase y está confirmada su asistencia, y se va rellenando poco a poco mediante se van sacando los datos de la votacion, esto convierte el array en un array bidimensional en el cual tienes el de candidatos y votos para  cada candidato, siendo como posibilidad 0 o 1, 0 siendo que ese voto no va para esa persona y 1 a la persona que si, pudiendo despues contar cuantros votos ha sumado cada uno con un sumatorio del array de votos.

## Pila de votos (int)
- Por otro lado, los votos podrian contarse como una pila, ya que no tienen porque ir ordenados, simplemente pertenecer al array de candidatos, no necesitan un orden ya que al final lo que vas a necesitar va a ser la suma total de cada voto de cada candidato.