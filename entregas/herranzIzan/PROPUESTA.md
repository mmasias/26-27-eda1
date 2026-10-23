# Reto 004 - Refactoring de la propuesta

## 1. Tabla de decisiones

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
|---|---|---|---|---|
| Candidaturas propuestas | ListaEnlazada de String | Antes de cerrar la boleta pueden presentarse, retirarse o rechazarse candidaturas. Necesito añadir y eliminar nombres sin mantener un tamaño fijo. | Array fijo. Las retiradas obligarían a desplazar elementos o mantener huecos. | Buscar o eliminar una candidatura cuesta O(n). Las retiradas solo se permiten antes del cierre. |
| Boleta definitiva | String[] | Una vez terminada la inscripción, las candidaturas deben permanecer fijas durante la votación. | Lista enlazada. No necesitamos modificar las candidaturas una vez cerradas. | No se permiten candidaturas nuevas ni retiradas después del cierre. |
| Censo electoral | ListaEnlazada de String | Necesito registrar los DNI de los alumnos autorizados antes de comenzar la elección y comprobar posteriormente su pertenencia al censo. | Array fijo. Necesitaría conocer desde el principio el número exacto de alumnos. | Las búsquedas por DNI cuestan O(n). El censo se cierra al cerrar la boleta, por decisión de diseño. |
| Alumnos presentes | ListaEnlazada de String | La asistencia cambia durante la clase porque pueden llegar alumnos tarde o marcharse antes de votar. | Array fijo. Las altas y bajas resultarían menos cómodas de gestionar. | Cada búsqueda y eliminación puede requerir recorrer toda la lista, con coste O(n). |
| Votantes registrados | ListaEnlazada de String | Necesito conservar los DNI de quienes ya han votado, incluso si abandonan el aula, para impedir un segundo voto. | Boolean dentro de Alumno. Dependería del estado de cada objeto y sería menos independiente del control de asistencia. | Comprobar si un DNI ya ha votado cuesta O(n). La lista registra participación, nunca la opción elegida. |
| Orden de llegada | Cola de String | Los alumnos deben ser atendidos en el mismo orden en que llegan a la urna, aunque varios estén esperando simultáneamente. | Pila. Atendería primero al último alumno que llega. | La cola no representa tiempos reales de espera y permite que un DNI se encole más de una vez, aunque el doble voto se rechaza al atenderlo. |
| Urna electoral | Pila de String | Mantengo la idea original de apilar papeletas y extraerlas posteriormente, utilizando las operaciones de pila estudiadas en clase. | Cola. Conservaría el orden de entrada durante el recuento, algo innecesario para calcular los resultados. | Una urna real no funciona necesariamente como una pila. El orden LIFO puede facilitar inferencias sobre los votos si se conoce el orden de depósito. |
| Recuento de votos | int[] | Tras cerrar la boleta conocemos el número de candidaturas y necesitamos un contador fijo para cada una, más blanco y nulo. | Lista enlazada de enteros. Añadiría nodos y recorridos sin necesitar un tamaño variable. | Para localizar una candidatura por nombre hay que recorrer la boleta, con coste O(n). |

## 2. Cambios respecto a mi propuesta original

### Decisiones que mantengo

Mantengo el uso de un array para las candidaturas definitivas porque, una vez cerrada la boleta, no deben modificarse durante la elección.

También mantengo una lista enlazada para gestionar alumnos, aunque ahora separo el censo de los presentes para distinguir quién tiene derecho a votar de quién está realmente en el aula.

Mantengo la idea de utilizar una pila para las papeletas, pero reconozco que reproduce una simplificación del proceso y no el comportamiento exacto de una urna real.

El recuento sigue utilizando un array de enteros. Es adecuado porque el número de opciones ya es conocido y permanece fijo.

### Decisiones que cambio

En mi propuesta original, el profesor recorría los pasillos y recogía los votos. Ahora cada alumno se acerca a la urna y se incorpora a una cola.

También separo las candidaturas propuestas de las definitivas. Primero utilizo una lista enlazada para permitir retiradas y rechazos. Después construyo la boleta fija.

Antes consideraba la abstención una opción de la papeleta. Ahora la abstención significa no votar. Los votos en blanco y nulos se cuentan por separado.

Inicialmente quería incluir el DNI dentro de cada papeleta para detectar duplicados. He cambiado esta decisión porque permitiría relacionar a cada persona con su voto.

Ahora compruebo el DNI antes del depósito y lo registro independientemente de la opción elegida.

También cambio la invalidación de toda la elección ante un doble voto. En esta simulación se rechaza únicamente el intento irregular para poder continuar el proceso.

Por último, el recuento ya no se actualiza al depositar las papeletas. El secretario debe extraerlas de la urna y anotarlas posteriormente.

### Decisiones adoptadas de los ejemplos

Adopto el uso explícito de una cola para organizar la llegada de los alumnos a la urna, porque representa mejor el nuevo procedimiento que el recorrido del profesor por los pasillos.

También organizo las estructuras y sus operaciones en clases independientes, reutilizando la clase Nodo para la lista, la pila y la cola.

## 3. Supuestos del programa

- El censo se prepara antes de cerrar la boleta.
- Solamente pueden votar alumnos incluidos en el censo y presentes en el aula.
- Los alumnos que llegan tarde pueden votar si la urna sigue abierta.
- Los alumnos que se marchan pierden la posibilidad de votar mientras estén ausentes.
- Cada DNI puede depositar como máximo una papeleta.
- Las candidaturas solamente pueden retirarse o rechazarse antes del cierre.
- Una papeleta en blanco no contiene ninguna candidatura elegida.
- Una papeleta nula no cumple las condiciones establecidas para ser válida.
- La abstención consiste en no depositar una papeleta.
- Las anotaciones equivocadas se corrigen antes de continuar el escrutinio.
- Si varios candidatos comparten el máximo de votos, se declara empate.
- Los identificadores D1, D2, etc., representan DNI ficticios.

## 4. Comprobaciones de seguridad

Antes de aceptar una papeleta se comprueba:

1. Que la votación esté abierta.
2. Que el DNI pertenezca al censo.
3. Que el alumno esté presente.
4. Que el DNI no figure entre los votantes registrados.
5. Que no se haya alcanzado el número máximo de papeletas permitido.

Cuando se acepta el voto, se deposita únicamente la opción elegida y el DNI se registra por separado.

Al cerrar la urna se comprueba que el número de papeletas coincida con los depósitos aceptados y los DNI registrados, y que no supere el censo.

La proclamación solo se permite cuando todas las papeletas han sido contadas y no quedan errores de anotación pendientes.

Estas comprobaciones reducen irregularidades en la simulación, pero no convierten el programa en un sistema electoral real ni garantizan anonimato absoluto.

## 5. Limitaciones generales

El programa está diseñado para una elección pequeña de clase y utiliza estructuras implementadas manualmente, sin java.util.

Las búsquedas en las listas enlazadas son lineales.

El censo y la asistencia se verifican mediante identificadores ficticios, sin autenticar documentos de identidad reales.

La cola representa el orden de atención, pero no impide que un alumno se incorpore varias veces. El segundo depósito sí se rechaza.

La urna utiliza una pila LIFO, que no mezcla las papeletas y puede comprometer el secreto del voto si se conoce su orden de entrada.

La clase Ejemplo conoce el orden de los votos porque construye una simulación determinista. Esto permite verificar el recuento, pero no reproduce plenamente la privacidad de una votación real.

El sistema comprueba la cantidad de papeletas, pero no tiene un mecanismo externo de auditoría que garantice que ninguna haya sido sustituida.

El secretario puede cometer una anotación errónea. El programa la detecta al comparar la anotación con la papeleta extraída y permite corregirla.

## 6. Conclusión

La nueva propuesta utiliza ocho estructuras de datos principales, cada una asociada a una necesidad del proceso electoral.

He mantenido las decisiones que seguían siendo adecuadas y he modificado aquellas que no representaban correctamente la asistencia, la votación o el escrutinio.

La finalidad es simular una elección coherente con las estructuras de datos estudiadas en clase, haciendo explícitas tanto las decisiones de diseño como sus limitaciones.

