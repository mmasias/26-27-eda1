# Propuesta revisada del proceso electoral

<div align=center>

|Dato|Decisión|Justificación|Alternativa descartada|Limitación asumida|
|-|-|-|-|-|
|Alumnos presentes en el aula|Lista enlazada de objetos alumno|La presencia de alumnos es dinámica; permite registrar alumnos que llegan tarde insertándolos y alumnos que se marchan eliminándolos en cualquier momento.|Arreglo de tamaño fijo, porque exige definir una capacidad previa y desplazar elementos ante salidas o llegadas intermedias.|La búsqueda y eliminación exigen un nivel de cómputo adicional al recorrer la lista secuencialmente, aunque por el contexto este costo computacional es despreciable.|
|Candidaturas registradas|Lista con objetos (nombre en String y votos en int)|Es dinámico, puede tener una cantidad n de candidatos/opciones. Debido a la aleatoriedad de elementos que está sujeta a tiempo de compilación y ejecución, permite agregar opciones, retirar candidaturas y rechazar candidaturas.|Arreglo estático de opciones, porque no permite redimensionar ni eliminar candidaturas retiradas sin dejar huecos o reorganizar índices.|Localizar un candidato exige un nivel de cómputo adicional de búsqueda secuencial frente a los arreglos, pero por el contexto este costo computacional es despreciable.|
|Registro de votantes que ya votaron|Lista enlazada de votantes|Permite registrar secuencialmente a los alumnos que ya emitieron su papeleta y verificar mediante búsqueda si el votante ya existe para impedir el intento de votar dos veces.|Arreglo de tamaño prefijado, porque se desconoce la cantidad final de votantes que ejercerán el voto.|La verificación previa al voto requiere recorrido lineal completo en el peor caso a medida que crece la lista.|
|Urna de papeletas|Cola sobre lista enlazada|Las papeletas depositadas (válidas, en blanco y nulas) deben extraerse para el escrutinio en el mismo orden en que fueron introducidas en la urna.|Pila sobre lista enlazada, porque extraería las papeletas en orden inverso al que fueron depositadas.|Solo se accede a la papeleta del frente; no se pueden consultar papeletas intermedias sin desencolar.|
|Anotaciones del recuento|Pila sobre lista enlazada|Permite corregir una anotación errónea en el recuento deshaciendo inmediatamente la última acción registrada en el escrutinio.|Lista con búsqueda arbitraria, porque permitiría alterar votos antiguos en lugar de ceñirse a revertir el último error cometido.|Solo se puede deshacer la última anotación; no se pueden corregir errores previos sin desapilar las anotaciones posteriores.|
|Proclamación y empates|Lista con objetos candidato|Permite almacenar una cantidad n de candidatos ganadores cuando se produce un empate con el número máximo de votos.|Variable simple de candidato, porque asume un único vencedor y descarta la posibilidad de empate múltiple.|Requiere dos pasadas secuenciales: una para determinar el voto máximo y otra para recopilar a todos los que empatan.|

</div>

## Cambios respecto a mi propuesta

### Decisiones que mantengo
- Mantengo la **lista con objetos** para las candidaturas y opciones elegidas, almacenando el nombre (String) y el número de votaciones (int). Se mantiene porque es dinámico, puede tener una cantidad n de candidatos/opciones y debido a la aleatoriedad de elementos que está sujeta a tiempo de compilación y ejecución, permite buscar candidatos y agregarlos cuando no existen. Una lista exige un nivel de cómputo adicional que los arreglos no, pero por el contexto este costo computacional es despreciable.

### Decisiones que cambio
- En mi propuesta previa solo existía una lista general de opciones y un método directo para votar. Se cambia para separar cada dato del enunciado en una estructura propia:
  - Se añade la lista de alumnos presentes para gestionar llegadas tardías, retiradas y comprobar presencia antes de votar.
  - Se añade la lista de votantes para registrar quién votó y bloquear el intento de votar dos veces.
  - Se añade la cola para la urna, evitando contabilizar votos al vuelo y permitiendo registrar papeletas en blanco y nulas en orden de llegada.
  - Se añade la pila de anotaciones para permitir deshacer y corregir una anotación errónea en el recuento.
  - Se añade la lista de proclamación para almacenar múltiples candidatos en caso de empate.
