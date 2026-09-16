Planteamiento inicial:

Se utilizara el proyecto anterior del carrefur 
pero en este caso solo se trasta de la simulacion del compartamiento de la fila con las nuevas caracteristicas mencionadas. 

Tiempo de 0 a 4 horas 
Cada minito llega una persona con propababilidad de 0.6
Apertura de caja libre cada minuto ccofn probasbilidad de 0.4 

Si la persona lleva esperando 8 minutos tiene la probabilidad de aburrirse y irse de la fila. La probabilidad es del 30% de aburrirse he irse y cuestionarlo cada 5 minutos.

Si llega alguien con derecho preferencial (embarazo, tercera edad o discapacidad) se incorpora justo despues de la ultima persona con el mismo derecho o al frente si nadie en la fila tiene ese derecho aun.

Tambien alguien se puede colar detras de un conocido que ya esta en la fila, en la posicion inmediata siguiente a la del conocido, sin derecho alguno que lo justifique. 

La fila no puede tener mas de 30 posiciones, no es bloqueante. Entonces si llega otra persona que se quiere incorporar en la fila y val ser tan grande puede desistir y no unirse. 


Tambien puede que alguien le de sus compras a otra persona que se encuentre en la fila. 

Cada 15 minutos simulados, si la fila supera a los 25 personas, se escucha por los parlantes "Pasen por esta caja en orden de fila"

Reto 

Simularr el escenario completo (dos 2 horas, con las reglas nuevas activas desde el mnuto 20)

Cada minuto imprimir el tamaño (lonngitud) de la filaç



Planteamiento ordenado:

Parámetros y Probabilidades Base

Llegada de clientes: Cada minuto, 60% de probabilidad de que llegue una persona.

Caja libre: Cada minuto, 40% de probabilidad de que una caja atienda/libere a la persona al frente de la fila.

Capacidad: Máximo 30 personas. Si llega alguien y la fila supera este umbral (o está muy llena), la persona desiste de entrar.

Comportamientos Especiales y Eventos

Aburrimiento (Abandono): Si un cliente suma 8 minutos esperando, a partir de ese momento hay un 30% de probabilidad de que se vaya, evaluándolo cada 5 minutos transcurridos.

Fila Preferencial: Embarazadas, adultos mayores o personas con discapacidad se insertan justo después de la última persona preferencial (o en el primer lugar si no hay ninguna).

Colados: Una persona puede ingresar e insertarse justo detrás de un conocido existente en la fila.

Transferencia de compras: Un cliente puede ceder/fusionar sus compras con otra persona ya presente en la fila.

Anuncio de altavoz (Caja rápida): Cada 15 minutos simulados, si la fila tiene más de 25 personas, se atiende un grupo bajo la instrucción "Pasen por esta caja en orden de fila".

Condiciones del Reto

Duración total: 2 horas (120 minutos).

Activación de reglas nuevas: A partir del minuto 20.

Output requerido: Imprimir el tamaño/longitud de la fila cada minuto.