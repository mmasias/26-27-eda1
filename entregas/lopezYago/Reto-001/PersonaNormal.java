package entregas.lopezYago.Reto-001;

public class PersonaNormal extends Persona {

    public PersonaNormal(String nombre, int minutoLlegada) {
        super(nombre, minutoLlegada);
    }

    @Override
    public boolean esPreferente() {
        return false;
    }
}