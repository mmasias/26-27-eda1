package entregas.lopezYago.Reto-001;

public class PersonaPreferente extends Persona {

    public PersonaPreferente(String nombre, int minutoLlegada) {
        super(nombre, minutoLlegada);
    }

    @Override
    public boolean esPreferente() {
        return true;
    }
}