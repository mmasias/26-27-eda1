# Reto 004 - Refactoring de la propuesta

Parto de mi propuesta de Elecciones (PR #110), donde usaba un array para los votos, una cola para los votantes y descartaba la lista, y del enunciado de pyElecciones.

Supuestos que tomo:
- Si un alumno se marcha ya no puede proponerse ni votar, pero si ya había votado su papeleta se queda en el montón.
- Cuando se cierra el plazo los candidatos quedan fijados aunque alguno se vaya después.
- La papeleta es secreta, solo lleva el nombre votado y no el de quien la entrega.
- Una papeleta sin nombre la guardo como texto vacío.

## Propuesta revisada

|Dato|Decisión|Justificación|Alternativa descartada|Limitación asumida|
|-|-|-|-|-|
|Alumnos presentes|Lista (`presentes`)|<sub>"Llegan alumnos tarde y otros se marchan": no se sabe cuántos habrá y se sale desde cualquier posición. La operación que más se repite es preguntar si alguien está, antes de proponerse o de votar.|<sub>Array de matriculados con una marca de presente: el enunciado no da la lista de matriculados ni su tamaño, y el array arrastraría a los ausentes.|<sub>Saber si alguien está obliga a recorrer la lista (coste lineal): la simulación lo muestra al rechazar un voto ("recorridos 7 presentes"). Con el tamaño de un aula es aceptable.|
|Candidaturas con el plazo abierto|Lista (`candidaturas`)|<sub>"Un candidato puede retirarse y pueden proponerse otros": mientras el plazo está abierto el número cambia y el que se retira puede estar en cualquier sitio.|<sub>Un array desde el principio: hasta cerrar el plazo no se sabe cuántos candidatos habrá.|<sub>Para comprobar que no se propone dos veces hay que recorrerla. Al cerrar el plazo hay que copiarla a un array (`cerrarCandidaturas`).|
|Opciones de la pizarra: candidatos fijados, en blanco y nulo|Array de `String` (`opciones`, en `Pizarra`)|<sub>"Al cerrar el plazo, los candidatos quedan fijados": desde ese momento el tamaño es fijo (candidatos + 2) y no hay altas ni bajas.|<sub>Seguir con la lista: su flexibilidad ya no hace falta y el recuento necesita llegar a cada opción por posición.|<sub>El secretario canta un nombre, no un número: para saber qué casilla es hay que recorrer el array (`posicionDe`, lineal). Si un candidato se marcha después del cierre, sigue en la pizarra.|
|Palitos de cada opción|Array de `int` paralelo (`palitos`): la posición `i` cuenta los votos de la opción `i`|<sub>"Añade un palito en su columna por cada voto cantado": sumar en una casilla ya conocida cuesta lo mismo con 2 que con 30 candidatos. "Si se equivoca al anotar, borra el palito": corregir es restar uno en esa casilla.|<sub>Guardar el contador dentro de cada nodo de la lista de candidatos: para cada voto habría que recorrerla.|<sub>Los dos arrays tienen que ir alineados. Borrar un palito solo sabe de qué columna es, no de qué papeleta salió; y nunca puede bajar de 0 (`assert` en `borrarPalito`).|
|Papeletas recogidas sin leer|Pila (`papeletas`)|<sub>"Deja las papeletas recogidas una sobre otra, formando un montón" y "el secretario las toma una a una": solo se usa la de encima, la última que se dejó.|<sub>Cola (solución A de los ejemplos): para leerlas en el orden de recogida habría que sacar la de abajo del montón, y el orden de lectura no cambia el recuento. Array o lista: dan acceso a cualquier posición, algo que nadie usa.|<sub>Se leen al revés de como se recogieron (la de Rosa, la última, es la primera en cantarse). Una papeleta leída sale de la pila: para volver a contarlas habría que haberlas guardado aparte. La simulación lo muestra al proclamar ("montón vacío").|
|Alumnos que ya han votado|Lista (`yaHanVotado`)|<sub>"Cada alumno vota una vez": antes de aceptar una papeleta hay que saber si esa persona ya entregó una. Crece con cada voto y no se sabe cuántos votarán.|<sub>Cola de votantes (mi propuesta): una cola solo deja ver al primero, así que para saber si alguien ya votó habría que vaciarla. Marcar "ha votado" en la papeleta: rompería el voto secreto.|<sub>Comprobarlo es recorrer la lista (lineal): la simulación lo muestra al rechazar el segundo voto de Pepito ("recorridos 5 votantes"). Sabe quién votó, pero no qué votó.|

## Cambios respecto a mi propuesta

- El array para contar los votos lo mantengo, porque los candidatos ya no cambian y llegar a una casilla por su posición es inmediato. Pero en mi propuesta decía que "los candidatos se conocen antes de votar y no cambian" y eso solo es verdad después de cerrar el plazo. Ahora la pizarra tiene dos arrays paralelos (nombres y palitos) y dos opciones más, en blanco y nulo, que pide el enunciado. El array de nombres lo saco de los ejemplos, sin él la pizarra no sabe qué columna es el nombre que canta el secretario.
- La cola de votantes la cambio por una lista de quién ha votado. Yo decía que "los alumnos votan por orden de llegada" pero el enunciado no habla de ninguna fila ni de que el orden importe. Lo que sí dice es que cada uno vota una vez, y para eso hay que preguntar "¿este ya votó?", y eso con una cola no se puede sin vaciarla.
- La lista ya no la descarto, la uso para presentes y candidaturas. Yo decía que una lista sirve cuando "se mete o se saca gente por el medio, y aquí eso no pasa", pero en el enunciado sí pasa: llegan alumnos tarde, otros se van y los candidatos se retiran. Esto lo cojo de los ejemplos, me convenció que el tamaño de esos datos cambia durante el proceso.
- Añado una pila para las papeletas, que en mi propuesta ni salían. De las dos soluciones de los ejemplos me quedo con la B porque el enunciado habla de un montón y el secretario coge siempre la de encima.

## Simulación

El código está en src/elecciones. Cada estructura del código es una fila de la tabla:
- Nodo: el nodo de la lista y de la pila
- Lista: presentes, candidaturas y quién ha votado (inserta y elimina con nodo dummy)
- Pila: el montón de papeletas
- Pizarra: los dos arrays (opciones y palitos), anota, borra y proclama mirando si hay empate
- Eleccion: el proceso con sus fases (candidaturas, secretario, votación, escrutinio, proclamación), decide qué se acepta y qué se rechaza
- Ejemplo: ejecuta la simulación

Lo que pasa en la simulación:
- Carlos llega tarde y Luisito se marcha
- Marta retira su candidatura y la de Luisito se rechaza porque ya no está
- Marta vota en blanco y Carlos vota a "Batman" (nulo)
- Luisito intenta votar después de irse
- Pepito intenta votar otra vez
- Se canta "Pepito", el presidente anota a Anita por error y borra el palito
- Al final Anita 2 y Pepito 2, empate y hay que repetir

Para ejecutarlo, desde src:
```
javac -encoding UTF-8 -d out elecciones/*.java
java -ea -cp out elecciones.Ejemplo
```

## Retos extendidos

Abstención: al final informarAbstencion recorre presentes y cuenta los que no están en yaHanVotado. En la simulación sale 1 de 7 (Lucas). Cuesta presentes x votantes comparaciones porque por cada presente se recorre la otra lista. No se puede sacar leyendo papeletas porque el que no vota no deja papeleta y además las papeletas no llevan el nombre de quien la entrega. Una en blanco es un voto, no una abstención. Y para leerlas habría que vaciar la pila.

Lo que rebato de los ejemplos: en las soluciones A y B, votar solo mira que el alumno esté en presentes, así que no cumple "cada alumno vota una vez" del enunciado. Si Pepito entrega dos papeletas se cuentan las dos, y en la tabla no lo ponen como limitación. Les falta guardar quién ha votado ya (yo uso la lista yaHanVotado), separado de la papeleta para que el voto siga siendo secreto.

También la solución A dice que la cola "facilita reconstruir el proceso si hay que revisarlo", pero como la papeleta no lleva el nombre de quien vota, leerlas en el orden de recogida no te deja reconstruir nada que no te dé también la pila.
