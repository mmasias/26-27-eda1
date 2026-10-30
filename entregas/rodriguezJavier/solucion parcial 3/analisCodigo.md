# 5. Análisis de código – GestorPersonas

## A. ¿Qué problemas potenciales ves en este código respecto al manejo de referencias?

El principal problema es que el gestor puede compartir referencias a objetos `Persona` con código externo.

Si la lista utiliza la implementación B del nodo, al insertar una persona se guarda directamente su referencia. Por tanto, si modificamos el objeto original después de insertarlo, también se modifica la persona almacenada en la lista.

Además, el método `buscarPersona` devuelve directamente la referencia al objeto almacenado. Esto permite que quien recibe esa referencia pueda modificar los datos internos de la lista sin pasar por el gestor.

## B. ¿Cómo lo mejorarías para evitar modificaciones no deseadas de los objetos?

Utilizaría el constructor de copia de `Persona` tanto al insertar como al devolver una persona.

Al insertar, crearía una copia para que los cambios realizados sobre el objeto original no afecten a la lista.

Al buscar, devolvería otra copia para evitar que quien recibe la persona pueda modificar directamente el objeto almacenado.

De esta manera, conseguimos proteger los datos de la lista frente a modificaciones externas no deseadas.