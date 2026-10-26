package entregas.AguilarAdriana;

public class Principal {
    public static void main(String[] args) {
        Persona manuel = new Persona("Manuel", "1234");
        Persona copia = new Persona(manuel);
        Persona referencia = manuel;

        Lista lista = new Lista();
        lista.insertarPersona(manuel);
        lista.insertarPersona(copia);
        lista.insertarPersona(referencia);

        System.out.println("manuel == copia: " + (manuel == copia));
        System.out.println("manuel == referencia: " + (manuel == referencia));

        manuel.cambiarNombre("Manuel Antonio");
        System.out.println("Lista tras cambiar el nombre de manuel:");
        lista.mostrar();

        Lista lista2 = new Lista();
        Persona juan = new Persona("Juan", "1234");
        lista2.insertarPersona(juan);
        Persona personaEncontrada = lista2.obtenerPersona("1234");
        personaEncontrada.cambiarNombre("Juan Manuel");
        System.out.println("Nombre en la lista: " + lista2.obtenerPersona("1234").obtenerNombre());

        juan.cambiarNombre("Juan Carlos");
        System.out.println("Tras cambiar juan: " + lista2.obtenerPersona("1234").obtenerNombre());
    }
}
