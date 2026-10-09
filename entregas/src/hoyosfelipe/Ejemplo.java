public class Ejemplo {
    private Console console;

    public Ejemplo() {
        console = new Console();
    }

    public static void main(String[] args) {
        new Ejemplo().ejecutar();
    }

    public void ejecutar() {
        console.writeln("== Eliminar repetidos");
        this.probarEliminarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 });
        this.probarEliminarRepetidos(new int[] { 1, 1, 1 });
        this.probarEliminarRepetidos(new int[] { 1, 2, 2 });
        this.probarEliminarRepetidos(new int[] { 1, 2, 3 });
        this.probarEliminarRepetidos(new int[] { 5, 5, 6, 6 });
        this.probarEliminarRepetidos(new int[] {});

        console.writeln("== Fusionar");
        this.probarFusionar(new int[] { 1, 4, 7 }, new int[] { 2, 3, 8, 9 });
        this.probarFusionar(new int[] {}, new int[] { 2, 3 });
        this.probarFusionar(new int[] {}, new int[] {});
        this.probarFusionar(new int[] { 1, 1 }, new int[] { 1 });
    }

    private void probarEliminarRepetidos(int[] datos) {
        ListaEnlazada conDummy = this.crear(datos);
        ListaEnlazada sinDummy = this.crear(datos);
        this.mostrar("entrada:   ", conDummy);

        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();

        this.mostrar("con dummy: ", conDummy);
        this.mostrar("sin dummy: ", sinDummy);
        console.writeln();
    }

    private void probarFusionar(int[] datosA, int[] datosB) {
        ListaEnlazada a = this.crear(datosA);
        ListaEnlazada b = this.crear(datosB);
        this.mostrar("a:         ", a);
        this.mostrar("b:         ", b);

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        this.mostrar("resultado: ", resultado);
        this.mostrar("a despues: ", a);
        this.mostrar("b despues: ", b);
        console.writeln();
    }

    private void mostrar(String titulo, ListaEnlazada lista) {
        console.write(titulo);
        lista.imprimirLista();
    }

    private ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }
        return lista;
    }
}
