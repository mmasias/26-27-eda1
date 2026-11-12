# Enmienda
### Colas con pilas 
En el archivo pila entregado consta de varios fallos que impiden su funcionalidad debido a que he seguido un enfoque que no era el que se preguntaba. Esto se debe a que he definido como funciona la clase pila pero no he desarollado los metodos de la clase cola, que es lo que se pedia. 

Estos errores se corregiran en un nuevo archivo que cumplira con la propuesta planteada 

algunos errores criticos de mi codigo son 
```
public pila(){
        int valor = null;
        this.valor;
        valor ++;
    }
```
En esta parte ``int valor = null`` lo declaro como un int pero al ser un null no lo debo definir con un int 

el valor ``valor++`` no tiene sentido meterlo en un constructor pero hay q recordar que esto es un pseudocodigo 

```
     public void apilar(int valor){
        Valor valor = newValor;
        this.valor;
        get.next();
        valor.siguiente;
     }
```
en el metodo apilar el ``newValor`` deberia haberse planteado como un ``new Valor(valor)``, ademas de que la clase valor no esta definida en el pseudo 

el ``get.next`` el get no esta definido y el next() no esta implementado 

en ``valor.siguiente`` el siguiente no esta implementado 

```public int desapilar(){
        valor = null;
         valor != estaVacia();
         assert estaVacia() "La pila esta vacia";
         return -1;
     }
```
en desapilas el ```valor = null``` sobra por lo anterior
tambien aplico una sentencia boolean dentro del fragmento 

```public boolean estaVacia(){
        this.valor;
        return true;
     }
```
en el boolean estaVacia(), ``this.valor`` se vuelve una expresion suelta y ``return true`` se vuelve ineccesaria 

Una forma de corregir este codigo sin cambiarlo mucho es la siguiente
este codigo se basa en el pseudocodigo del examen sin embargo tambien he creado otro codigo corregido que funciona de manera mas enficiente que el siguiente
````

public class pilacor {
    private Nodo cima;

    private class Nodo {
        private int valor;
        private Nodo siguiente;

        public Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    public pilacor() {
        this.cima = null;
    }

    public void apilar(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = this.cima;
        this.cima = nuevo;
    }

    public int desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila esta vacia");
        }

        int valor = this.cima.valor;
        this.cima = this.cima.siguiente;
        return valor;
    }

    public boolean estaVacia() {
        return this.cima == null;
    }

    public static void main(String[] args) {
        pilacor pila = new pilacor();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        System.out.println(pila.desapilar());
        pila.apilar(4);
        System.out.println(pila.desapilar());
        System.out.println(pila.desapilar());
    }
}
````