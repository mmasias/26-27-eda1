class Main {

    public static void main(String[] args) {
        Console console = new Console();

        ArrayEnlazado numeros = new ArrayEnlazado(5);
        console.writeln("Longitud: " + numeros.longitud());
        numeros.imprimir();

        numeros.asignar(0, 10);
        numeros.asignar(2, 30);
        numeros.asignar(4, 50);
        numeros.imprimir();

        console.writeln("En el indice 2 hay: " + numeros.obtener(2));
        console.writeln("En el indice 1 hay: " + numeros.obtener(1));

        numeros.asignar(2, 99);
        numeros.imprimir();
    }
}