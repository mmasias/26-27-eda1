public class PruebasCola {
    public static void main(String[] args) {
        Cola miCola = new Cola();
        
        miCola.encolar(1);
        miCola.encolar(2);
        miCola.encolar(3);
        
        System.out.println("Primer desencolar (Esperado: 1) Obtenido: " + miCola.desencolar());
        
        miCola.encolar(4);
        
        System.out.println("Segundo desencolar (Esperado: 2) Obtenido: " + miCola.desencolar());
        System.out.println("Tercer desencolar (Esperado: 3) Obtenido: " + miCola.desencolar());
    }
}