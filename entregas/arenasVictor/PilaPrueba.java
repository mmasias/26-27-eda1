public class PilaPrueba {
    public static void main(String[] args) {
        Pila pila = new Pila();

        pila.apilar(1);             
        pila.apilar(2);               
        pila.apilar(3);               
        assert pila.desapilar() == 3; 
        pila.apilar(4);               
        assert pila.desapilar() == 4;
        assert pila.desapilar() == 2; 
        assert pila.desapilar() == 1;
        assert pila.estaVacia();

        System.out.println("Todo correcto");
    }
}
