public class PilaBasePrueba {

    public static void main(String[] args) {
        PilaBase nuevaPila = new PilaBase();

        nuevaPila.apilar(1);
        nuevaPila.apilar(2);
        nuevaPila.apilar(3);
        nuevaPila.mostrar();
        nuevaPila.desapilar();
        nuevaPila.mostrar();
        nuevaPila.apilar(4);
        nuevaPila.mostrar();
        nuevaPila.desapilar();
        nuevaPila.desapilar();
        nuevaPila.mostrar();

    }
}
