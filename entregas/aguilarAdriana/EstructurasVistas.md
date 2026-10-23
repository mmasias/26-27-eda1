# Estructura de datos de votación

## Propuesta inicial (Array de votos + Array de candidatos)

La votación puede gestionarse mediante una lista. Cada nodo de la lista
contendría la información de un candidato y su cantidad de votos.

Sin embargo, para este caso resulta más claro utilizar un array de objetos
`Candidato`. De esta forma, cada objeto almacena el nombre del candidato y la
cantidad de votos que ha obtenido.

## Array de objetos `Candidato`

El array se inicializa con la cantidad de elementos = candidatos que existan en la votación. Cada posición contiene un objeto `Candidato`.

```java
class Candidato {
    private String nombre;
    private int votos;

    public Candidato(String nombre, int votos) {
        this.nombre = nombre;
        this.votos = votos;
    }
}
```

Esta estructura nos permite relacionar a la persona con la cantidad de datos sin tener que reservar posiciones de memoria de
arrays para los votos nulos o las abstenciones.

## Votos nulos y abstenciones

Estos votos no pertenecen a ningun candidato por lo que se tienne que almacenar en memorias aparte

```java
int votosNulos;
int votosAbstenciones;
```

Estas variables guardarían la cantidad total de votos nulos y de abstenciones cuando se ejecute la votación.

## Estructura final

La estructura propuesta estaría formada por:

- Un array de objetos `Candidato`.
- Una variable de enteros `votosNulos`.
- Una variable de enteros `votosAbstenciones`.

También se puede usar un array adicional para almacenar esta
información, pero dos variables independientes hacen que el código sea más
claro y expresan mejor el significado de los datos.