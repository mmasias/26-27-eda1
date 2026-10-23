# Reto 004 - Refactoring de la propuesta

## Supuestos

El enunciado deja algunos casos sin definir. Estos son los supuestos que adopto:

- Una candidatura se rechaza si se propone a alguien que no está presente en ese momento.
- Quien vota y luego se marcha: su papeleta cuenta, pero deja de constar como presente.
- Si hay empate, no se proclama a nadie y se informa del empate.

## Propuesta revisada

<div align=center>

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
|-|-|-|-|-|
| Alumnos presentes | `List` | Llegan alumnos tarde y otros se marchan, así que el tamaño cambia durante el proceso. La operación frecuente es comprobar si alguien está presente antes de aceptar una candidatura o una papeleta. | Array con todos los matriculados y un indicador de presencia: arrastra a los ausentes. | Comprobar la presencia recorre la lista (coste lineal), aceptable con el tamaño de un aula. |
| Candidaturas (plazo abierto) | `List` | Mientras el plazo está abierto se proponen y se retiran candidatos, así que el tamaño varía. | Array desde el inicio: el número final de candidatos no se conoce hasta cerrar el plazo. | Retirar un candidato exige buscarlo recorriendo la lista. |
| Candidatos fijados + blanco + nulo | Array de `String` en `Pizarra` | Al cerrar el plazo el tamaño queda fijo y ya no hay altas ni bajas. | Mantener la lista: ofrece una flexibilidad que ya no se necesita. | Hay que copiar los elementos de la lista al array al cerrar el plazo, con un bucle a mano. |
| Papeletas pendientes de lectura | `Stack` | El presidente las deja una sobre otra formando un montón y el secretario toma siempre la de encima, así que solo se usa un extremo. | `Queue`: leería por orden de recogida, algo que el enunciado no necesita porque el recuento sale igual. | La lectura invierte el orden de recogida, y una papeleta leída sale de la pila, así que revisarla después exige guardarla aparte. |
| Quiénes ya votaron | `List` | Hay que saber si alguien ya entregó papeleta, y ese dato debe seguir existiendo aunque esa persona se marche. | Un indicador "ya votó" dentro de la lista de presentes: se pierde si el alumno se marcha. | Detectar el doble voto recorre la lista (coste lineal). |
| Votos por opción | Array de `int` en `Pizarra` | El tamaño es fijo y cada voto incrementa una posición conocida. Corregir un error es decrementar esa posición. | Lista de contadores: añade una flexibilidad innecesaria. | Se canta el nombre y no el índice, así que localizar la posición recorre el array de nombres (coste lineal). |

</div>

## Cambios respecto a mi propuesta

### Mantengo

- **Lista para los alumnos.** El conjunto cambia durante el proceso, y eso sigue pidiendo tamaño variable.
- **Array de enteros para el recuento.** El número de opciones es fijo desde el cierre del plazo y cada posición corresponde a una opción.

### Cambio

- **Pila y cola a la vez → solo pila.** Las papeletas forman un único montón y se toman por un extremo, así que no necesito dos estructuras para lo mismo. Elijo la pila porque el enunciado dice que se dejan "una sobre otra, formando un montón", y la pila reproduce ese montón.
- **Candidatos como array desde el inicio → lista que pasa a array al cerrar el plazo.** Mi propuesta suponía que las opciones se conocían antes de empezar, pero el enunciado permite retiradas y nuevas propuestas mientras el plazo está abierto.
- **Abstención como opción del array → dato calculado.** La abstención no es un voto: es alguien presente que no entregó papeleta, así que no puede aparecer como opción en la pizarra.
- **Faltaba la opción "en blanco".** El enunciado la contempla, junto con "nulo".

### Añado

- **Lista de quiénes ya votaron**, para detectar el intento de votar dos veces. Mi propuesta no tenía ningún dato que lo permitiera.

## Correspondencia con el código

| Fila de la tabla | Estructura en el código |
|-|-|
| Alumnos presentes | `List presentes` |
| Candidaturas (plazo abierto) | `List candidaturas` |
| Candidatos fijados + blanco + nulo | `String[] nombres` en `Pizarra` |
| Papeletas pendientes de lectura | `Stack papeletas` |
| Quiénes ya votaron | `List votantes` |
| Votos por opción | `int[] votos` en `Pizarra` |
