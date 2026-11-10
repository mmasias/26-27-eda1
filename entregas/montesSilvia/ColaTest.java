public class ColaTest {
    public static void main (String [] args) {
        Cola cola= new Cola ();
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        int resultado1= cola.desencolar ();
        assert resultado1 ==1;
        cola.encolar (4);
        int resultado2 = cola.desencolar ();
        assert resultado2 == 2;
        int resultado3= cola.desencolar ();
        assert resultado3 == 3;
        assert cola.estaVacia ();
        System.out.println ("Todas las pruebas han sido superadas.");
    }
}