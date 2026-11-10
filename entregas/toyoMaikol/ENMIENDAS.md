## Registro de enmiendas (10/11/2026)

### Enmienda en método desapilar()

En el diseño en papel especifiqué que iteraría guardando los valores hasta llegar al último valor != null. Esto es inviable en Java porque la estructura provista Cola maneja datos de tipo primitivo int, el cual no admite valores null. 

Para corregirlo he decidido extraer el elemento con desencolar() e inmediatamente verificar si la cola original se quedó vacía usando cola.estaVacia().

### Enmienda en método estaVacia()

En mi diseño indiqué que comprobaria que el método desencolar sobre mi atributo cola fuera != null. Además del fallo de null en primitivos (explicado arriba), llamar a desencolar() altera el estado interno (destruye un dato) simplemente para hacer una comprobación.

Para preservar los elementos y devolver la respuesta correcta, he decidido modificar la función para que simplemente delegue la pregunta a la cola encapsulada, retornando cola.estaVacia().

## Aclaración

Con respecto a la implementación de la clase Cola, he decidido asumir el comportamiento detrás de los métodos públicos brindados en el examen para poder hacer pruebas de mi clase Pila.