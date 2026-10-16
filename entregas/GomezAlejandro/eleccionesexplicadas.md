# Elección a delegado

En la elección a delegado, el array tiene entre 37 y 40 elementos y su tamaño es fijo desde que se crea, así que se reserva con capacidad de sobra. Cada posición guarda el recuento de votos de una categoría: los resultados por candidato ocupan las posiciones de los 3 candidatos, y el resto sirve para el voto nulo y la abstención, de modo que cada voto emitido incrementa en uno el valor de su posición. La variable que declaramos es una referencia al array y no el array en sí, así que asignarla a otra variable no copia el recuento, sino que ambas apuntan al mismo array. Los 34 alumnos del censo se guardan en una lista, que a diferencia del array puede crecer o decrecer si cambia el grupo, y los que esperan para votar forman una cola, en la que se atiende primero al que llegó antes. Las papeletas depositadas en la urna se tratan como una pila, de modo que en el recuento se saca primero la última que entró y su voto se suma a la posición correspondiente del array. Al terminar, un método estático recorre las posiciones de los candidatos y devuelve la del valor más alto, que identifica al nuevo delegado.



Array = Recuento de votos.

Lista = Alumnos censados para el voto.

Cola = Fila de alumnos para votar.

Pila = La urna.
