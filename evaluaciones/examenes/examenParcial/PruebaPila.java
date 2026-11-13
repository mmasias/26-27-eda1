public class PruebaPila {

    public static void main(String[] args) {
        Pila pila = new Pila();

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        int primeroSacado = pila.desapilar();
        System.out.println("Primer desapilar() -> Esperado: 3 | Obtenido: " + primeroSacado + " | Correcto: " + (primeroSacado == 3));