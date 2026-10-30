public class solucionIntegridadDatos {

    private Lista lista;

    public solucionIntegridadDatos() {
        lista = new Lista();
    }

    public void agregarPersona(Persona persona) {

        if (!lista.contiene(persona)) {

            Persona copia = new Persona(persona);
            lista.insertarPersona(copia, false);
        }
    }

    public Persona buscarPersona(String dni) {

        Persona encontrada = lista.obtenerPersona(dni);

        if (encontrada != null) {
            return new Persona(encontrada);
        }

        return null;
    }
}