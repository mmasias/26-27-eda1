# Estructura visas y justificaciones

## Lista con objetos
```java

class Candidatos {
    String nombre;
    int votos;

    public Candidatos();

    public votar(nombreCandidato);
}

Candidatos elecciones = new Candidatos();
elecciones.votar('San Miguel');

```

- Almacena el nombre de los candidatos/opción elegida (string) y el número de votaciones (int).
- Es dinámico, puede tener una cantidad n de candidatos/opciones.



## Justificación
- Debido a la aleatoriedad de elementos que está sujeta a tiempo de compilación**,** he decidido recomendar listas con objetos.
- Una lista exige un nivel de cómputo adicional que los arreglos no, pero por el contexto este costo computacional es despreciable.
