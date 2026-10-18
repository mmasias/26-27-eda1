# Elecciones
El ejercicio de las elecciones lo planteo de la siguiente forma: array de objetos "candidato" que guarde el número de votos en cada objeto creado.

# Justificación
- Al inicializar el sistema, el tamaño del array será exactamente el número de participantes (escalable a N candidatos). Cada posición guardará un objeto con la información exclusiva de un participante real, aprovechando la velocidad de acceso de los arreglos.

- Como el voto en blanco y nulo no es un "candidato", realizar un objeto de tipo candidato es romper el principio de "es un" ya que un voto nulo no es un candidato, la solución mas práctica es la de realizar un contador global "int votosBlancos" y al terminar la simulación llamar esa variable para contar los votos.

# Planteamiento del problema
-El programa funcionará de la siguiente manera: se pedirá el número de votantes totales y el número de candidatos que entrarán a la votación. Luego a cada candidato se le asignará el nombre que pida el usuario y se crearán los objetos. Al momento de votar, se llamará a una función recibirVoto() que añadirá +1 voto al candidato seleccionado en su variable int cantidadVotos. Si se vota nulo/blanco, se sumará al contador global previamente mencionado. Al finalizar, se sumarán todos los votos registrados; si la suma coincide con la variable global numeroDeVotosTotales, el programa arrojará el resultado. En caso contrario, alertará sobre una inconsistencia en los datos.
