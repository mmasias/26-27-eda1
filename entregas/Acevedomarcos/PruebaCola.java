public class PruebaCola {

    public static void main(String[] args) {

        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        System.out.println(cola.desencolar());

        cola.encolar(4);

        System.out.println(cola.desencolar());
        System.out.println(cola.desencolar());

        System.out.println(cola.desencolar());

        System.out.println(cola.estaVacia());
    }
}
