# Elección 
### Array de votos (int) 
- Almacena los votos de los candidatos 
- Almacena además los votos nulos y las abstenciones 
- Tamaño de inicialización : # candidatos + 2

### Array de candidatos (String)
- Almacena los nombres de los candidatos 
- Tamaño de inicialización : # candidatos
## Justificación 
Cada uno escribió su voto en un papel y todos los papeles se juntaron en un montón, que funciona como una pila porque al hacer el conteo se va cogiendo siempre el papel de arriba. Para el recuento usamos dos arrays: uno de String con los nombres de los 3 candidatos y otro de int de tamaño 5 (candidatos + 2) donde cada posición guarda los votos del candidato que está en esa misma posición del primer array, y las dos últimas guardan los nulos y los blancos. Por cada papel se suma 1 en la casilla que toca, y al final la suma de todo el array de votos tiene que dar 35.