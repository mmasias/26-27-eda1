import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
 
public class Ejemplo {
 
    public static void main(String[] args) {
        int[][] casos = {
            {1, 1, 2, 3, 3, 4},
            {1, 1, 1},
            {1, 2, 2},
            {1, 2, 3},
            {5, 5, 6, 6},
            {} 
        };
 
        for (int[] caso : casos) {
            ListaEnlazada conDummy = construir(caso);
            ListaEnlazada sinDummy = construir(caso);
 
            System.out.print("Entrada:                    ");
            conDummy.imprimirLista();
 
            conDummy.eliminarRepetidos();
            sinDummy.eliminarRepetidosSinDummy();
 
            String salidaConDummy = capturarImpresion(conDummy);
            String salidaSinDummy = capturarImpresion(sinDummy);
 
            System.out.print("eliminarRepetidos:          " + salidaConDummy);
            System.out.print("eliminarRepetidosSinDummy:  " + salidaSinDummy);
            System.out.println(salidaConDummy.equals(salidaSinDummy) ? "Coinciden" : "NO COINCIDEN");
            System.out.println("------------------------------------------------");
        }
    }
 
    static ListaEnlazada construir(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = valores.length - 1; i >= 0; i--) {
            lista.insertarAlPrincipio(valores[i]);
        }
        return lista;
    }
 
    static String capturarImpresion(ListaEnlazada lista) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(buffer));
        lista.imprimirLista();
        System.setOut(original);
        return buffer.toString();
    }
}