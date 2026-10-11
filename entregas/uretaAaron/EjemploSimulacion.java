package listas.simulacion;

public class EjemploSimulacion {

    public static void main(String[] args) {

        System.out.println("=== 1. LISTA QUE SIMULA SER UN ARRAY ===");
        ListaQueSimulaArray arraySimulado = new ListaQueSimulaArray(5);

        System.out.print("Estado inicial: ");
        arraySimulado.imprimir();

        arraySimulado.set(0, 10);
        arraySimulado.set(2, 30);
        arraySimulado.set(4, 50);

        System.out.print("Tras set(0,10), set(2,30), set(4,50): ");
        arraySimulado.imprimir();

        System.out.println("Valor en indice 2 (get): " + arraySimulado.get(2));
        System.out.println("Tamano fijo: " + arraySimulado.length());

        System.out.println("\n=== 2. ARRAY QUE SIMULA SER UNA LISTA ===");
        ArrayQueSimulaLista listaSimulada = new ArrayQueSimulaLista(3);

        listaSimulada.insertarAlFinal(1);
        listaSimulada.insertarAlFinal(2);
        listaSimulada.insertarAlFinal(4);
        System.out.print("Lista simulada inicial: ");
        listaSimulada.imprimir();

        listaSimulada.insertarEn(2, 3);
        System.out.print("Tras insertar 3 en pos 2: ");
        listaSimulada.imprimir();

        listaSimulada.eliminarEn(0);
        System.out.print("Tras eliminar pos 0: ");
        listaSimulada.imprimir();
        System.out.println("Tamano dinamico actual: " + listaSimulada.tamano());
    }
}