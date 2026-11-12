public class ExamenParcialCorregido {

    
    private static class Nodo {
        final int dato;
        final Nodo abajo;

        Nodo(int dato, Nodo abajo) {
            this.dato = dato;
            this.abajo = abajo;
        }
    }

    
    private static class Pila {
        private Nodo cima;
        private int tamaño;

        void apilar(int valor) {
            cima = new Nodo(valor, cima);
            tamaño++;
        }

        int desapilar() {
            if (cima == null) {
                throw new IllegalStateException("La pila está vacía");
            }
            int valor = cima.dato;
            cima = cima.abajo;
            tamaño--;
            return valor;
        }

        boolean estaVacia() {
            return tamaño == 0;
        }

        int tamaño() {
            return tamaño;
        }
    }

    private static class Cola {
        private final Pila entrada = new Pila();
        private final Pila salida = new Pila();

        void encolar(int valor) {
            entrada.apilar(valor);
        }

        int desencolar() {
            if (salida.estaVacia()) {
                for (int i = entrada.tamaño(); i > 0; i--) {
                    salida.apilar(entrada.desapilar());
                }
            }
            return salida.desapilar();
        }

        boolean estaVacia() {
            return entrada.estaVacia() && salida.estaVacia();
        }
    }

    public static void main(String[] args) {
        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        System.out.println(cola.desencolar()); // 1
        cola.encolar(4);
        System.out.println(cola.desencolar()); // 2
        System.out.println(cola.desencolar()); // 3
    }
}