# Elecciones

Para las elecciones de delegado considero que lo mejor sería un array de enteros para llevar el recuento de los votos.

## Justificación

El voto es anónimo, por lo cual solo sería necesario almacenar el número de votos por posición en el array. El tamaño del array debería de ser la cantidad de candidatos + 2 para ajustar a los votos nulos y abstenciones.

```java

final int CANDIDATOS_POSTULADOS = 3;
int[] votosPorCandidato = new int[CANDIDATOS_POSTULADOS + 2];

```

Considero que una lista no hace falta, pues al momento de las elecciones, se conoce de forma fija la cantidad de candidatos postulados y no es dinámica. En caso de presentarse un candidato "nuevo" (como alguien escribiendo un nombre que no pertenece) simplemente se considera un voto nulo.

## Alternativa con objetos

Otra forma sería con un array de una clase `Candidato` la cual tiene como atributos los nombres de los candidatos, junto con los votos que han recibido. Esto permite también conocer los nombres de los candidatos, los cuales son importantes al momento de anunciar los resultados.

```java
class Candidato {
    private String nombre;
    private int votos;
    /*...*/
}

final int CANDIDATOS_POSTULADOS = 3;
Candidato[] candidatos = new Candidato[CANDIDATOS_POSTULADOS + 2];
```

## Una pila para los votos

En caso de querer guardar *el voto* y su información asociado a cada uno de los candidatos, se puede modelar como una pila. Como ejemplo práctico se puede tomar el recuento por "rayas": los votos se van escribiendo al final, y en caso de errores se borran del final.