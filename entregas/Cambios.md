## Cambios respecto a mi propuesta anterior

- **Se mantiene la cola para las papeletas.** Sigo usando una cola (FIFO) en vez de una pila (LIFO). Aunque en la realidad las papeletas se amontonan una encima de otra, en una votación lo importante es poder reconstruir el recuento paso a paso, y para eso hay que leerlas en el orden en que se emitieron.

- **Se mantiene el paso de lista a array en las candidaturas.** Mientras el plazo está abierto, los candidatos se pueden proponer o retirar, y la lista lo hace fácil. Al cerrarse, el conjunto ya no cambia, así que lo paso a un array de tamaño fijo, que es más sencillo de consultar.

- **Limitación reconocida: voto único y abstención.** La lista de `presentes` solo sirve para comprobar que quien vota está en el aula. De momento, el programa no impide que un alumno vote dos veces ni calcula la abstención. Una mejora posible sería guardar quién ha votado ya y compararlo con los presentes.