# Máquinas expendedoras

Las máquinas expendedoras de la -2. (Casi) todo el mundo las ha sufrido, pero ¿qué ocurre por dentro?. 

Este ejercicio consiste en averiguarlo y en escribirlo con las estructuras vistas: listas, pilas, colas y, si se justifica, árboles.

***El código es la última parte del trabajo.*** La primera es mirar, la segunda es decidir y poder explicar por qué. Modelar es eso: decidir qué importa de la realidad, cómo entra, cómo se guarda y cómo sale para luego escoger la estructura cuyo comportamiento se parece al de la máquina.

## 2Do

Tres reglas que valen para todo el ejercicio:

1. **Sin modelo no hay código.** Antes de escribir la primera clase se entrega la tabla de modelado (fase 2).
2. **Toda estructura se justifica.** Para cada una se escribe qué otra se consideró y por qué se descartó. "Porque era la que sabía" no es una razón.
3. **Se prefiere un modelo pequeño y defendible a uno grande y confuso.** Un núcleo que funciona y se explica vale más que diez extensiones a medias.

## Qué hay que modelar

Las máquinas de la -2.

**Núcleo (obligatorio):**

- La máquina tiene varios canales. Cada canal guarda unidades de un único producto, con un precio, y tiene un código (33, 45, 23...).
- Se pueden insertar monedas de 5, 10, 20 y 50 céntimos, y de 1 y 2 euros. El saldo insertado se acumula.
- Se selecciona un código. Si el canal existe, tiene unidades y el saldo alcanza, se dispensa el producto y se devuelve el cambio.
- El cambio se paga con las monedas que la máquina tiene guardadas, nunca con monedas inventadas.
- El usuario puede cancelar y recuperar lo insertado.

**Reglas de la realidad que el modelo debe respetar:**

- Una unidad repuesta hoy no debe salir antes que una repuesta ayer.
- Las monedas del cambio se guardan separadas por valor, con una capacidad limitada por tipo.
- Si una moneda llega a un tipo ya lleno, no se puede usar para cambio.

**Extensiones (opcionales, en este orden):**

1. Pago con tarjeta: no mueve monedas y tiene sus propios estados (aprobado, rechazado, tiempo agotado).
2. Historial de ventas.
3. Menú por categorías en la pantalla.

## Fases

<div align=center>

|Fase|Qué se hace|Qué se practica|
|:-:|-|-|
|1|Observar la máquina real y anotar|Distinguir lo que importa de lo que no|
|2|Rellenar la tabla de modelado|Entra / se guarda / sale, y elegir estructura|
|3|Implementar el núcleo|Traducir un modelo a código|
|4|Probar los casos límite|Pensar en lo que rompe el modelo|
|5|Extensiones y nota final|Ampliar sin romper|

</div>

### Fase 1: observar

Mirar: Se anota, con el mínimo de palabras y con dibujos:

- Qué se guarda dentro y en qué orden se reponen los productos.
- Qué ocurre cuando se inserta una moneda y cuando se pulsa cancelar.
- Qué pasa si no hay cambio, si el producto se agotó o si el código no existe.

No hace falta saber cómo funciona el mecanismo real. Basta con describir lo que se ve desde fuera y lo que cabe deducir.

### Fase 2: la tabla de modelado

Es el entregable más importante. Una fila por cada cosa que la máquina guarda:

<div align=center>

|Qué se guarda|Cómo entra|Cómo sale|Estructura elegida|Alternativa descartada y por qué|
|-|-|-|-|-|
|(ejemplo) Unidades de un canal|Por atrás, al reponer|Por delante, al vender|Cola|Pila: saldría antes la más nueva|

</div>

Reglas de la tabla:

- Mínimo tres filas y al menos una de cada una de las estructuras estudiadas.
- Toda fila trae su alternativa descartada, aunque sea obvia.
- Se decide si el árbol tiene sitio en este modelo. Las dos respuestas (sí y no) valen si se razonan.

### Fase 3: el núcleo

Se implementa con las estructuras estudiadas, sin recursión y sin colecciones del lenguaje que oculten el comportamiento (se usan las propias de la asignatura). Cada estructura aparece en el código con el mismo nombre que en la tabla.

### Fase 4: casos límite

Cada caso se ejecuta y se imprime lo que ocurre:

<div align=center>

|Caso|Pregunta que obliga a decidir|
|-|-|
|Saldo insuficiente|¿Se pide más dinero o se rechaza?|
|Canal agotado|¿Qué se devuelve y qué se imprime?|
|Código inexistente|¿Se pierde el saldo?|
|Sin monedas para el cambio|¿Se vende igualmente o se rechaza y se devuelve lo insertado?|
|Tubo de monedas lleno|¿A dónde va la moneda que sobra?|
|Cancelar a mitad de operación|¿Con qué monedas se devuelve el saldo?|

</div>

> ***2Think:***
> El cambio con "la moneda mayor que quepa primero" siempre da una respuesta, pero no siempre la mejor. ¿Existe una situación en la que ese método falle teniendo monedas suficientes? ¿Importa para una máquina de estas?

### Fase 5: extensiones y nota final

Cada extensión añadida se anota en la tabla de modelado como una fila nueva: si hace falta una estructura distinta, se justifica igual que las demás.

## Qué se entrega

- La tabla de modelado, antes del código.
- El programa, con la salida de cada caso de la fase 4.
- Una nota breve (una página) que responda: ¿qué decisión de modelado se cambió al implementar? ¿Cuál se mantuvo aunque costara?

## Cómo se valora

<div align=center>

|Aspecto|Qué se mira|
|-|-|
|Modelo|Tabla completa, con razones y alternativas|
|Coherencia|El código hace lo que la tabla dice|
|Casos límite|Cada uno decidido y explicado|
|Claridad|Nombres del dominio y métodos pequeños|

</div>

Se puede pedir que se explique cualquier fila de la tabla sin mirar el código.
