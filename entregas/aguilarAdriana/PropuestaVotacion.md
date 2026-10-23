# Propuesta de estructuras de datos para la elección del delegado

## Supuestos

La elección se realiza entre los alumnos presentes en el aula. La lista de
presentes se actualiza durante el proceso: pueden llegar alumnos tarde y
otros pueden marcharse. Por tanto, solo puede proponerse o votar quien esté
presente en el momento correspondiente.

El plazo de candidaturas se mantiene abierto durante una fase concreta.
Durante esa fase se pueden añadir candidatos y retirar candidaturas. Cuando
el plazo se cierra, el conjunto de candidatos queda fijado.

El profesor es el presidente de la elección: abre y cierra la votación,
recoge las papeletas, anota el recuento y proclama el resultado. Después de
cerrar las candidaturas, el profesor elige un secretario de entre los
presentes. Se asume que el secretario puede ser también candidato, porque el
enunciado no establece que deba quedar excluido.

Cada alumno presente puede votar una sola vez y lo hace en papel. Una
papeleta sin nombre es un voto en blanco, una papeleta con un nombre que no
pertenece a las candidaturas fijadas es un voto nulo.

## Decisiones sobre los datos

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
|---|---|---|---|---|
| Alumnos presentes | Lista de alumnos presentes | El conjunto cambia durante el proceso porque llegan alumnos tarde y otros se marchan; además, solo pueden proponerse y votar quienes estén presentes en ese momento. | Array de todos los alumnos matriculados con una marca de presencia, porque mantendría en el dato a personas ausentes y obligaría a interpretar una marca adicional cada vez que se comprueba la presencia. | Comprobar si un alumno está presente requiere recorrer la lista, con coste lineal, aceptable para el tamaño de un aula. |
| Altas y bajas de presencia | Operaciones de añadir y retirar alumnos de la lista mientras el proceso lo permita | La llegada y la salida de alumnos hacen que la pertenencia al conjunto de presentes no sea permanente durante toda la elección. | Mantener una lista inmutable desde el comienzo, porque no representaría las entradas ni las salidas que describe el enunciado. | Si un alumno se marcha después de votar, el modelo debe conservar por separado que su voto ya fue emitido para no contarlo otra vez. |
| Candidaturas abiertas | Lista de candidatos mientras el plazo está abierto | El número de candidatos no se conoce al principio y una candidatura puede añadirse o retirarse antes del cierre. | Array desde el comienzo, porque exigiría fijar un tamaño o gestionar posiciones vacías antes de conocer el conjunto definitivo. | Las candidaturas deben poder validarse y modificarse solo durante esta fase; después ya no se pueden cambiar. |
| Candidaturas cerradas | Array de candidatos al cerrar el plazo | Al cerrar el plazo, el conjunto deja de crecer o reducirse y su tamaño queda fijo; el recuento necesita acceder a cada opción por una posición estable. | Mantener la lista hasta el final, porque conservaría una flexibilidad que ya no se necesita durante el recuento y no ofrecería acceso directo por posición. | Es necesario copiar los candidatos de la lista al array cuando se cierra el plazo. |
| Secretario | Referencia a un único alumno elegido por el profesor después del cierre de candidaturas | El enunciado asigna a una sola persona la lectura de las papeletas y el canto de cada voto; elegirlo después del cierre permite escogerlo entre los presentes en ese momento. | Mantener varios secretarios, porque no se define cómo se repartirían las papeletas ni cómo se resolverían discrepancias entre recuentos. | Si el secretario se marcha antes de terminar, el enunciado no indica un procedimiento de sustitución. |
| Presidente | Referencia al profesor responsable de la elección | El profesor abre y cierra la votación, recoge las papeletas, anota el recuento y proclama el resultado; esas responsabilidades deben quedar asociadas a una persona concreta. | Tratar al profesor como un alumno supervisor más, porque no reflejaría que dirige las fases y ejecuta operaciones específicas del proceso. | La estructura identifica al responsable, pero no garantiza por sí sola que el profesor actúe correctamente. |
| Papeletas recogidas pendientes de lectura | Pila | Las papeletas se dejan una sobre otra formando un montón y el secretario las toma una a una; la última colocada es la primera disponible para leer. | Cola, porque leería primero la papeleta más antigua y no representaría el acceso por la parte superior del montón. | Una papeleta que ya sale de la pila no puede revisarse desde ella; para conservar un historial habría que almacenarla aparte. |
| Papeleta leída | Opción entre candidato, blanco o nulo | Cada papeleta produce exactamente una clasificación: nombre de un candidato, ausencia de nombre o nombre no incluido entre los candidatos. | Guardar solo el texto escrito, porque obligaría a repetir la interpretación cada vez que se consulte el resultado y no expresaría directamente la categoría del voto. | La clasificación depende de que el secretario lea correctamente el contenido de la papeleta. |
| Nombres de las opciones del recuento | Array de cadenas con los nombres de los candidatos y las opciones `blanco` y `nulo` | El conjunto de opciones queda fijo al cerrar las candidaturas y el presidente debe escribir una columna para cada candidato, además de blanco y nulo. | Lista, porque permitiría insertar o quitar opciones cuando el conjunto ya está fijado y no aportaría una ventaja al recuento. | La posición de cada nombre debe mantenerse alineada con la posición de su contador. |
| Votos de cada opción | Array de enteros paralelo al array de nombres, inicializado en cero | Cada voto cantado incrementa la columna correspondiente y el presidente puede identificar una posición concreta para corregir un palito equivocado. | Un contador independiente con una variable por candidato, porque no se adaptaría igual de bien si cambia el número de candidaturas y duplicaría la estructura del recuento. | Encontrar la posición de un nombre a partir de lo que canta el secretario exige recorrer el array de nombres, con coste lineal. |
| Corrección del recuento | Operación para decrementar el contador de la opción anotada por error | El enunciado permite borrar un palito cuando el presidente se equivoca; el dato debe poder corregirse sin rehacer todo el recuento. | Reiniciar todos los contadores y volver a leer las papeletas, porque sería innecesario cuando el error afecta a una anotación concreta. | Solo se puede corregir si se identifica qué opción recibió el palito incorrecto y se evita que el contador sea negativo. |
| Participación de cada alumno | Registro de que cada alumno ha emitido como máximo una papeleta | El enunciado establece que cada alumno vota una vez, por lo que el proceso debe poder detectar una segunda entrega de papeleta. | Aceptar papeletas sin comprobar al votante, porque no permitiría verificar el límite de un voto por alumno. | Relacionar la identidad del alumno con su voto podría reducir el secreto de la votación; se registra la participación, no la opción elegida. |
| Resultado final | Array de nombres junto con su array de contadores, incluyendo blanco y nulo | El presidente debe anotar el recuento de cada candidatura y de las dos clases especiales de papeleta, y después proclamar el resultado. | Publicar únicamente el nombre del candidato ganador, porque ocultaría los votos en blanco, nulos y los resultados del resto de candidatos. | El enunciado no establece cómo resolver un empate entre candidatos. |

## Orden de las fases

1. El profesor identifica y mantiene actualizada la lista de alumnos
   presentes.
2. Se abre el plazo de candidaturas. Los presentes pueden proponer
   candidatos, retirar su candidatura o proponer a otros alumnos presentes.
3. El profesor cierra el plazo y convierte la lista de candidaturas en el
   array definitivo.
4. El profesor elige al secretario entre los presentes.
5. El profesor abre la votación. Cada alumno presente que aún no haya votado
   deposita una papeleta.
6. El profesor deja las papeletas formando un montón, representado por la
   pila.
7. El secretario extrae las papeletas una a una y canta cada voto.
8. El profesor incrementa el contador de la opción correspondiente. Si se
   equivoca, borra el palito y decrementa ese contador.
9. El profesor cierra la votación, publica el recuento y proclama el
   resultado.

## Cambios respecto a mi propuesta

### Decisiones que se mantienen

- Se mantiene la relación entre cada candidato y su cantidad de votos. En
  ambas propuestas el resultado debe indicar cuántos votos obtiene cada
  candidato, no solo el total de papeletas.
- Se mantienen contadores independientes para las opciones que no son
  candidatos. En la propuesta anterior eran `votosNulos` y
  `votosAbstenciones`; en la nueva propuesta  siguen siendo contadores
  separados porque esas papeletas no deben sumarse a ningún candidato.

### Decisiones que cambian

- Se cambia el array fijo de objetos `Candidato` por una lista durante el
  plazo de candidaturas y por un array definitivo al cerrarlo. La propuesta
  anterior solo contemplaba un array ya inicializado; la nueva necesita
  añadir y retirar candidatos antes de fijar el conjunto final.
- Se cambia la inicialización de `votos`. Antes el constructor recibía el
  número de votos; ahora cada candidato comienza con `0` y el recuento se
  modifica mediante operaciones de incrementar y corregir el contador.
  Así, los votos representan el proceso real y no un dato conocido antes de
  leer las papeletas.
- Se cambia la distinción entre votos nulos y abstenciones por la
  clasificación del enunciado: una papeleta sin nombre es voto en blanco y
  un nombre que no es candidato es voto nulo. La modificación es necesaria
  porque la propuesta anterior no contemplaba la categoría de voto en blanco
  ni la regla concreta para clasificar una papeleta.
- Se añaden los alumnos presentes como dato del proceso. La propuesta
  anterior solo almacenaba candidatos y contadores, pero ahora deben
  representarse las personas que pueden proponer y votar, incluyendo sus
  entradas y salidas del aula.
- Se añaden el profesor presidente y el secretario. La propuesta anterior no
  representaba sus responsabilidades; la nueva debe registrar quién dirige
  la votación y quién lee las papeletas.
- Se añade la pila de papeletas. La propuesta anterior no representaba las
  papeletas ni su orden físico; la pila refleja que se dejan formando un
  montón y se extraen desde arriba.
- Se añade el control de participación y la corrección del recuento. El
  nuevo proceso exige un voto por alumno y permite borrar un palito si el
  presidente se equivoca, mientras que esas operaciones no aparecían en la
  propuesta anterior.

La propuesta sigue la propiedad de cada dato: tamaño variable para
los presentes y las candidaturas abiertas, tamaño fijo para el recuento
cerrado, acceso por el extremo superior para las papeletas y acceso por
posición para los contadores.
