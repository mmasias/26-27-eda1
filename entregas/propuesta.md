
Dato	Decisión	Justificación	Alternativa descartada	Limitación asumida
Dato: Alumnos presentes	
Decisión: Lista (List)	
Justificación:Los alumnos se registran en un orden determinado y es necesario comprobar si cada uno está presente.	
Alternativa descartada:Un array, porque sería menos flexible para eliminar alumnos durante el proceso.	
Limitación asumida:Hay que actualizar la lista cuando cambia la asistencia.

Dato: Eliminación de alumnos presentes	
Decisión:Método delete()	
Justificación:Luisito deja de constar como presente y no debe participar en las siguientes fases.	
Alternativa descartada:Mantenerlo en la lista, porque permitiría que un alumno ausente superase la comprobación de asistencia.	
Limitación asumida:La eliminación depende de que el método identifique correctamente al alumno.

Dato:Incorporación de alumnos	
Decisión:Método insertEnd()	
Justificación:Carlos se añade al final, conservando el orden de incorporación de los alumnos.	
Alternatica descartada:Insertarlo al principio, porque alteraría el orden de los registros.	
Limitación asumida:El orden depende de la secuencia de inserción.

Dato:Candidaturas	
Decisión:Lista (List)	
Justificación:Las candidaturas se incorporan progresivamente y se necesita comprobar si un alumno ya ha sido propuesto y eliminar candidaturas.	
Alternativa descartada:Un array, porque gestionar las incorporaciones y eliminaciones sería menos flexible.	
Limitación asumida:Es necesario validar cada propuesta antes de incorporarla.

Dato:Validación de candidaturas	
Decisión:Método proponer() y contains()	
JustificaciónSolo pueden presentarse alumnos que constan como presentes y que todavía no aparecen en la lista de candidatos.	
Alternativa descartada:Aceptar todas las propuestas, porque permitiría candidatos ausentes o repetidos.	
Limitación asumida:La validación depende de que la lista de presentes esté actualizada.

Dato:Eliminación de candidaturas	
Decisión:Método delete()	
Justificación:Marta se elimina antes de obtener la lista definitiva de candidatos.	
Alternativa descartada:Mantener todas las candidaturas, porque no se respetaría la eliminación solicitada.	
Limitación asumida:La eliminación debe realizarse antes de crear la pizarra.

Dato:Candidatos definitivos	
Decisión:Array (String[])	
Justificación:Una vez finalizadas las candidaturas, se obtiene un conjunto de nombres que se pasa al constructor de Pizarra.	
Alternativa descartada:Pasar directamente la lista, porque el constructor utilizado en el código recibe un array.	
Limitación asumida:El tamaño del array no cambia automáticamente si se modifican las candidaturas posteriormente.

Dato:Resultados electorales	
Decisión:Objeto Pizarra	
Justificación:Alternativa descartada:Es necesario registrar los votos, mostrar los resultados y proclamar al ganador.	Alternativa descartada:Realizar todas estas operaciones directamente en main, porque mezclaría la ejecución principal con la gestión de los resultados.	
Limitación asumida:El recuento depende de la implementación de la clase Pizarra.

Dato:Urna electoral	
Decisión:Cola (Queue)	
Justificación:Las papeletas deben leerse en el mismo orden en que se recogieron, tal como indica el programa.	
Alternativa descartada:Una pila, porque devolvería primero la última papeleta introducida.	
Limitación asumida:Se debe mantener el orden de incorporación de las papeletas.

Dato:Contenido de las papeletas	
Decisión:Cadena (String)	
Justificación:El voto se representa con el nombre del candidato y una cadena vacía representa un voto en blanco.	Alternativa descartada:Utilizar números identificadores, porque sería necesario relacionarlos con los nombres de los candidatos.	
Limitación asumida:Una cadena puede contener un nombre que no corresponda a ningún candidato.

Dato:Validación de votantes	
Decisión:Método votar()	
Justificación:Solo se aceptan papeletas de alumnos que aparecen en la lista de presentes.	
ALternativa descartada:Aceptar todos los votos, porque permitiría votar a alumnos que no constan como presentes.	Limitación asumida:No se comprueba si un alumno presente ya ha votado anteriormente.

Dato:Votos a personas no candidatas	
Decisión:Validación del voto en la pizarra	
Justificación:Batman no aparece entre los candidatos, por lo que su voto no debería contabilizarse como válido.	
Alternativa descartada:Contabilizar cualquier nombre, porque podría alterar los resultados electorales.	
Limitación asumida:El código mostrado no valida al candidato en el método votar(); el tratamiento definitivo depende de la implementación de Pizarra.

Dato:Votos en blanco	
Decisión:Cadena vacía ("")	
Justificación:Una papeleta vacía se identifica y se muestra como voto en blanco.	
Alternativa descartada:Utilizar un nombre ficticio, porque podría confundirse con un candidato real.	
Limitación asumida:La cadena vacía queda reservada para representar los votos en blanco.

Dato:Presentación de los datos	
Decisión:Método viewLine()	
JustificaciónEs necesario mostrar los nombres de los presentes y los candidatos para comprobar visualmente el contenido de ambas listas.	
Alternativa descartada:Mostrar solo el número de elementos, porque no permitiría comprobar qué alumnos están registrados.	
Limitación asumida:La información se presenta por consola, sin una interfaz gráfica.
