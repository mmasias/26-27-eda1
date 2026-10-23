# Refactor de mi propuesta para las elecciones

## 1 Tabla de justificaciones

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
| :--- | :--- | :--- | :--- | :--- |
| **Alumnos Presentes** | Lista Enlazada | Como los alumnos pueden llegar tarde o irse en cualquier momento, la lista nos deja meter o sacar personas fácilmente sobre la marcha sin tener que mover todo de golpe | Array fijo Lo descarté porque si alguien se va, queda un espacio vacío (`null`) que nos enreda al recorrerlo y contar | Para ver si alguien está o si ya votó, hay que revisar la lista persona por persona desde el principio |
| **Candidaturas admitidas** | Lista Enlazada | Un candidato puede retirarse o ser rechazado en cualquier momento Con la lista lo sacamos fácil conectando al anterior con el siguiente | Array fijo Descartado porque no sabemos cuántos candidatos quedarán al final y dejaríamos espacios vacíos inútiles | Buscar a un candidato en específico toma un poco más de tiempo porque hay que ir uno por uno |
| **Papeletas físicas** | Pila | Funciona como una urna real: los votos caen unos sobre otros Al abrirla para contar, el último papel que entró es el primero que sacamos (LIFO) | Cola La descarté porque en una urna real no hay un "orden de llegada" estricto para sacar los papeles | No podemos ver ni contar un voto del fondo sin antes haber sacado todos los que están encima |
| **Recuento en pizarra** | Lista Enlazada | Necesitamos ir anotando y corrigiendo los votos Cada apunte guarda el nombre y sus votos juntos haciendo muy fácil sumar o corregir errores | Usar dos arrays (uno de nombres, otro de votos) Descartado porque si un candidato se retira, es un lío actualizar los dos lados a la vez | Para sumarle un voto a alguien, hay que leer la pizarra desde el principio hasta encontrar su nombre |

## 2 Cambios respecto a mi propuesta

**Decisiones descartadas:**
Dejo atrás mi idea de usar arreglos de tamaño fijo para los candidatos y los votos Como en las elecciones pasan cosas imprevistas (gente que llega tarde, salidas o retiros de candidatos), no podemos saber el tamaño exacto desde el principio Un array me obligaría a crear arreglos nuevos a cada rato, copiar datos y pelear con espacios `null`

**Decisiones adoptadas:**
Me paso a usar nodos, pilas y listas hechas a mano Además, decidí separar físicamente la urna (donde cae el voto secreto) de la pizarra (donde anotamos los resultados) Así funciona igualito que en la vida real: primero guardamos los papeles apilados y luego los vamos sacando para anotarlos en la pizarra

## 3 Retos Extendidos

### Reporte de Abstención
Para saber la abstención, hago una resta simple: `(Total de alumnos presentes) - (Total de papeletas en la urna)`

- **Estructuras empleadas:** Saco los presentes contando a la gente en la lista del censo al final de la jornada Y saco la cantidad de papeletas llevando un contador en la pila de la urna justo antes de abrirla
- **Justificación:** No se puede contar la abstención leyendo las papeletas porque el que se abstiene simplemente no mete nada en la urna (no hay "papel de abstención") Si metiera un papel en blanco, eso ya cuenta como un voto emitido Por eso, la única manera lógica de saber cuántos faltaron por votar es cruzando la asistencia final con la cantidad total de papeletas depositadas