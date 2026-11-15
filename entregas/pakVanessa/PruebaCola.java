public class PruebaCola {

    public static void main(String[] args) {

        Cola numeros = new Cola();

        assert numeros.estaVacia();

        numeros.encolar(10);
        numeros.encolar(20);
        numeros.encolar(30);

        assert !numeros.estaVacia();

        assert numeros.desencolar() == 10;

        numeros.encolar(40);
        numeros.encolar(50);

        assert numeros.desencolar() == 20;
        assert numeros.desencolar() == 30;
        assert numeros.desencolar() == 40;
        assert numeros.desencolar() == 50;

        assert numeros.estaVacia();

        System.out.println("Todas las pruebas fueron correctas.");
    }
}