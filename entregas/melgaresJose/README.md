# Elecciones - Reto 004

## Justificación para la solución


<div align=center>

|Dato|Decisión|Justificación|Alternativa descartada|Limitación asumida|
|-|-|-|-|-|
|Candidatos|Array que puede estar vacio|Puede presentarse una elección sin candidatos|La lista permite cambiar la cantidad de candidatos.|La cantidad de candidatos es fija una vez creado el array.|
|Papeletas|Pila|Al momento de contar los votos, estos se almacenan como una pila, emulando la forma en que se contarian en una pizarra con rayas. En caso de haber un error o discrepancia, se elimina el último ingresado.|La cola no emula correctamente el proceso al tratar de eliminar o corregir votos.|Los votos sin contar aun deberían almacenarse, y recrear el proceso desde 0 puede no ser efectivo|
|Alumnos en clase|Lista|La cantidad de alumnos no puede tomarse como los matriculados, es cambiante. Se debe de admitir cambios (o incluso polizontes)|Array, por los cambios que los alumnos presentes pueden sufrir|En una clase muy larga el acceso es lento.|
|Votos por candidatos|Array de `n` candidatos + 2 para admitir votos nulos + abastenciones de tipo `Candidato`|Los votos permiten el acceso aleatorio a la información del candidato|Dos arrays paralelos, una con la cuenta de votos y otro con la información de candidatos (en conflictto con la solución para Candidatos mencionada antes)|Se debe de delegar bien la responsabilidad de si es *el Candidato* quien *tiene* los votos |

</div>


## Cambios con respecto a su solución

Aunque la solución B si sigue la propuesta que doy respecto al uso de la pila para los votos, para la comparación usaré el modelo A.

- En vez de una lista fléxible durante el plazo abierto que luego convierte en array, yo propongo usar un array desde el inicio (que puede arrancar vacío o siempre estarlo)

- Elige una cola para mantener el orden estricto de llegada de los votos, mientras que yo opto por una pila para priorizar la facilidad de corregir errores sobre la marcha (tipo "deshacer"). 

- En su solucón se hace uso de dos arrays paralelos independientes, a diferencia de mi solución, que agrupa la información usando un único array de objetos de tipo Candidato (incluyendo nulos y abstenciones) para delegar mejor la responsabilidad de los votos.

- En el caso de los alumnos presentes coincido plenamente con usted, ya que ambos elegimos una lista por el comportamiento dinámico y cambiante de la asistencia en el aula.