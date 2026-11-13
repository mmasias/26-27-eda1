public class PruebaPila {

    public static void main(String[] args) {
        Pila pila = new Pila();

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        int primeroSacado = pila.desapilar();
        System.out.println("Primer desapilar() -> Esperado: 3 | Obtenido: " + primeroSacado + " | Correcto: " + (primeroSacado == 3));
        pila.apilar(4);

        int segundoSacado = pila.desapilar();
        System.out.println("Segundo desapilar() -> Esperado: 4 | Obtenido: " + segundoSacado + " | Correcto: " + (segundoSacado == 4));

        int terceroSacado = pila.desapilar();
        System.out.println("Tercer desapilar() -> Esperado: 2 | Obtenido: " + terceroSacado + " | Correcto: " + (terceroSacado == 2));

        System.out.println("¿La pila esta vacia al final? -> Esperado: false | Obtenido: " + pila.estaVacia());
    }
}