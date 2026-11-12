## Desviación 1: Clase privada

Me he equivocado al poner en el papel que iba a crear una clase privada. Me refería a que las pilas tenían que ser privadas para que el cliente no pudiera acceder a ellas directamente.

Por eso he creado una clase pública Cola con dos atributos privados de tipo Pila.

## Desviación 2: Número de pilas

En el papel planteé utilizar varios atributos de tipo Pila para almacenar los elementos de la cola, pero no expliqué correctamente cómo se iban a organizar.

He utilizado dos pilas para que los elementos salgan en el mismo orden en el que han entrado en la cola.

## Desviación 3: Invariante

Me he equivocado al indicar que el número de elementos era cuatro, ya que ese número puede cambiar al encolar y desencolar.

El invariante correcto es que los elementos de la pila de salida, desde la cima hasta el fondo, seguidos de los elementos de la pila de entrada, desde el fondo hasta la cima, representan el orden de la cola.

## Desviación 4: Estado de la cola

Me he equivocado en la tabla del punto 3 al poner que después de encolar el 4 el estado era 1, 2, 3, 4.

El estado correcto es 2, 3, 4, porque anteriormente ya había desencolado el 1.

Para comprobarlo he realizado las pruebas correspondientes, verificando que los valores devueltos son 1, 2 y 3 y que al final queda el 4.