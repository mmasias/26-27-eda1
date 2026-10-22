# Carpeta de entregas

Agregue aquí su carpeta de entregas en el formato apellidoNombre
# Reto 004: Simulación de las elecciones del aula


## Archivos

| Archivo | Descripción |
|---|---|
| `Nodo.java` | Nodo enlazado, usado por la lista y la pila |
| `Lista.java` | Lista enlazada con búsqueda, baja en cualquier posición y contador de comparaciones |
| `Pila.java` | Pila enlazada (apilar, desapilar) |
| `Pizarra.java` | Dos arrays paralelos (nombres y votos) con las columnas En blanco y Nulo |
| `Eleccion.java` | Proceso electoral completo: fases, votación, recuento, abstención y proclamación |
| `Ejemplo.java` | Simulación que ejecuta todos los eventos y los muestra por pantalla |
| `PROPUESTA.md` | Tabla de decisiones, cambios, abstención y rebate |

## Compilar y ejecutar

```bash
javac *.java
java Ejemplo
```

Requiere JDK 8 o superior. Sin dependencias.

## Correspondencia con la tabla de la propuesta

| Dato | Estructura en el código |
|---|---|
| Alumnos presentes | `Eleccion.presentes` (`Lista<String>`) |
| Candidatos con el plazo abierto | `Eleccion.candidatosPlazo` (`Lista<String>`) |
| Candidatos fijados y votos | `Pizarra` (arrays paralelos) |
| Secretario | `Eleccion.secretario` (campo `String`) |
| Papeletas recogidas | `Eleccion.urna` (`Pila<String>`) |
| Quién ya votó | `Eleccion.votantes` (`Lista<String>`) |
| Resultado y empate | `Eleccion.proclamar()` (sin estructura, dos recorridos del array) |

## Qué simula `Ejemplo`

- Un alumno que llega tarde (Fran) y otros que se marchan (Eva, Gus).
- Una candidatura rechazada (Gael, no está presente) y otra retirada (Carla).
- Una papeleta en blanco (Carla) y otra nula (Eva, vota a Carla, que ya no es candidata).
- Un intento de votar de alguien que no está presente (Eva, tras marcharse).
- Un intento de votar dos veces (Ana). Gus también lo intenta tras marcharse y volver.
- Una anotación errónea en el recuento y su corrección.
- La proclamación con empate (Ana y Beto, 2 votos cada uno).
- La abstención al cierre (Hugo).

## Dónde se ven los costes

| Coste o limitación | Dónde se ve |
|---|---|
| Búsqueda secuencial O(n) en presentes y votantes | `Lista.buscar` y `Lista.eliminar` cuentan comparaciones; `informeCostes` las muestra |
| Copia O(c) al cerrar el plazo | `Eleccion.cerrarPlazo` |
| Búsqueda por nombre en la pizarra O(c) | `Pizarra.buscar` |
| Leer es destructivo | El montón queda a 0 tras el recuento (`informeCostes`) |
| La abstención no sale de las papeletas | `informarAbstencion` compara presentes − papeletas con la abstención real |
| Candidatos fijados tras cerrar el plazo | `proponerCandidato` y `retirarCandidato` se rechazan al cerrar |
