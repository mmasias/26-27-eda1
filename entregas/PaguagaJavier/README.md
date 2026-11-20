## Enmiendas declaradas sobre el diseño congelado

1. Enmienda (Atributo de conteo tamano):

   * Qué fallaba en el diseño congelado: En la Parte 1 se planteó el método empujarValores(Cola cola, int valor) para reordenar los elementos, pero no se especificó ningún mecanismo para saber cuántos elementos debían rotarse dentro de la cola.

   * Cómo se comprobó: Al intentar implementar empujarValores sin estructuras auxiliares ni recursión, se constató que la interfaz de Cola no proporciona un método .tamano() o .longitud().

   * Qué cambia: Se incorpora el atributo private int tamano, respetando la restricción del examen que autoriza atributos de tipo int. Este contador se incrementa en apilar y se decrementa en desapilar.

2. Enmienda  (Corrección sintáctica en la rama else de apilar):

   * Qué fallaba en el diseño congelado: En la hoja manuscrita figuraba textualmente else encolar(int 1).

   * Cómo se comprobó: Error evidente de compilación y lógica; se trataba de una confusión con el primer valor de prueba del ejemplo (apilar(1)).

   * Qué cambia: Se corrige a this.cola.encolar(valor).

---

## Registro de desviaciones justificadas

1. Paso de parámetros a métodos internos: En la hoja se escribió la firma empujarValores(Cola cola, int valor) y desapilar(Cola c). Al ser métodos miembros de la clase Pila, la instancia de cola ya es accesible como atributo (this.cola), por lo que no es necesario pasarla por parámetro.

2. Precondición mediante assert: En el diseño congelado no se había anotado explícitamente la comprobación de la precondición en desapilar(). Se añade assert !estaVacia(); en estricto cumplimiento del enunciado.