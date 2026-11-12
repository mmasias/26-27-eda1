# Registro de desviaciones

**1. Cómo se "invierte" la cola.** Mi diseño decía que había que invertir el orden,
pero no decía cómo. En el código, apilar encola el valor y luego rota los
elementos que ya estaban, para que el nuevo quede en el frente. Para saber cuántas
veces rotar añadí int tamaño, que el enunciado permite.

apilar(3):  [2,1]  →  encolar 3  →  [2,1,3]  →  rotar 2  →  [3,2,1]

frente = cima
    
**2. Redacción de las operaciones.** En la Parte 1 escribí que apilar mete el valor
"al final de la pila" y que desapilar "elimina el primero". En el código, el valor
queda en la cima, y desapilar además lo devuelve (con assert !estaVacia()).
Mis diagramas ya ponían el último valor delante (3-2-1), así que el código sigue
los diagramas. El texto estaba mal redactado.

No hay enmiendas: el diseño es funcional. Lo comprobé con PilaPrueba,
y los desapilar() devuelven 3, 4, 2 y 1, como en mis diagramas.