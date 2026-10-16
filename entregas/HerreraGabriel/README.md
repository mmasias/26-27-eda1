# Estructura vistas y justificación

## Array de votos (int)
- Almacenar los votos de los candidatos
- Almacenar tambien los votos nulos y/o las abstenciones (blancos)
- Tamaño de inicialización: # candidatos + 2
- Contador de votos: # candidatos + 1, nulos + 1, abstenciones + 1

## Array de candidatos (String)
- Almacenar los nombres de los candidatos 
- Tamaño de inicialización: # candidatos

## Array de votantes (boolean)
- Controlar quien ya voto por cada papel leído (true/false)
- Tamaño de inicialización: totalAlumnos

## Array de porcentajes (double)
- Almacenar los porcentajes calculados de cada opcion
- Tamaño de inicialización: # candidatos + 2

## Variables
- ``totalValidos`` (int): Suma de votos a candidatos + blancos (nulos no cuentan)
- ``totaldeVotosEmitidos`` (int): Acumulador total de votos
- ``estadoVotacion`` (boolean): Permitir o no mas votos
- ``ganador`` (int): Indicar el candidato con mayor votacion
- ``empate`` (boolean): Detectar si hay colisión en el primer lugar (misma cantidad de votos entre 2 candidatos)
- ``minParaGanar`` (double): Porcentaje necesario para ganar (50%)  
- ``totalAlumnos`` (int): Total de alumnos presentes dentro de la clase 

## Codigo
# Agrupa todos los datos pertenecientes a un postulante en una sola entidad.
```java
public class Candidato {
    public String nombre;
    public int votos;
    public double porcentaje;

    public Candidato(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
        this.porcentaje = 0.0;
    }
}
```
# Representan el estado general y los datos necesarios para gestionar la votación:
```java
public class Eleccion {
    public Candidato[] candidatos;

    public boolean[] yaVoto;

    public int votosBlanco;
    public int votosNulo;
    public double porcentajeBlanco;
    public double porcentajeNulo;

    public int totalValidos;
    public int totaldeVotosEmitidos;
    public boolean estadoVotacion;
    public Candidato ganador;
    public boolean empate;
    public double minParaGanar;
    public int totalAlumnos;
```
- **`candidatos` (`Candidato[]`)**: Arreglo de objetos que almacena a los candidatos participantes. Cada elemento contiene el nombre, votos y porcentaje de un candidato individual.
- **`yaVoto` (`boolean[]`)**: Arreglo de control para evitar votos repetidos. Cada posición representa a un alumno e indica si ya emitió su voto (`true`) o no (`false`) (En caso que no fueran anónimos los votos).
- **`votosBlanco` (`int`)**: Contador específico para los votos en blanco o abstenciones.
- **`votosNulo` (`int`)**: Contador específico para los votos anulados o inválidos.
- **`porcentajeBlanco` (`double`)**: Almacena el porcentaje obtenido por los votos en blanco.
- **`porcentajeNulo` (`double`)**: Almacena el porcentaje correspondiente a los votos nulos.
- **`totalValidos` (`int`)**: Suma de los votos de los candidatos más los votos en blanco (los nulos no se cuentan).
- **`totaldeVotosEmitidos` (`int`)**: Total acumulado de papeles o votos ingresados.
- **`estadoVotacion` (`boolean`)**: Indica si aun se puede votar (`true`) o no (`false`).
- **`ganador` (`Candidato`)**: Referencia directa al objeto del candidato que obtuvo la mayor cantidad de votos.
- **`empate` (`boolean`)**: Señala si existe igualdad de votos en el primer puesto.
- **`minParaGanar` (`double`)**: Porcentaje mínimo requerido para determinar un ganador directo (definido en `50.0%`).
- **`totalAlumnos` (`int`)**: Cantidad de alumnos presentes en la clase.

# Se encarga de inicializar la clase y dejar todas las variables y estructuras preparadas para comenzar la elección
```java
    public Eleccion(int numCandidatos, int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
        this.candidatos = new Candidato[numCandidatos];
        this.yaVoto = new boolean[totalAlumnos];

        this.votosBlanco = 0;
        this.votosNulo = 0;
        this.porcentajeBlanco = 0.0;
        this.porcentajeNulo = 0.0;

        this.totalValidos = 0;
        this.totaldeVotosEmitidos = 0;
        this.estadoVotacion = true;
        this.ganador = null;
        this.empate = false;
        this.minParaGanar = 50.0;
    }
```
- **Dimensionamiento de arreglos**: Reserva el espacio en memoria para `candidatos` según la cantidad de postulantes y para `yaVoto` con base en el total de alumnos.
- **Reinicio de contadores**: Establece los votos y porcentajes en `0` o `0.0` para asegurar que el conteo empiece limpio.
- **Configuración de estado inicial**: 
  - Abre la votación (`estadoVotacion = true`).
  - Asigna `ganador = null` y `empate = false` porque aún no hay votos registrados.
  - Fija el porcentaje mínimo para ser elegido (`minParaGanar = 50.0`) y guarda el total de alumnos (`totalAlumnos`).
