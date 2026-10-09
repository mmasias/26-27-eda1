public class Array
{
    private Nodo primero;
    private int numeroDatos;
    private final int capacidadMaxima;

    public Array(int capacidadMaxima)
    {
        if (capacidadMaxima <= 0)
        {
            assert false : "La capacidad máxima debe ser mayor que cero.";
        }
        this.primero = null;
        this.numeroDatos = 0;
        this.capacidadMaxima = capacidadMaxima;
    }

    public int obtenerCapacidadDisponible()
    {
        return this.capacidadMaxima - this.numeroDatos;
    }

    public void añadir(int valor)
    {
        añadir(valor, numeroDatos);
    }

    public void añadir(int valor, int posicion)
    {
        assert posicion >= 0 && posicion <= numeroDatos : "La posición está fuera de rango.";

        if (posicion < numeroDatos)
        {
            Nodo actual = primero;
            for (int i = 0; i < posicion; i++)
            {
                actual = actual.siguiente;
            }
            actual.valor = valor;
        }
        else
        {
            assert hayEspacioDisponible() : "Se ha alcanzado la capacidad máxima del array.";
            Nodo nuevoNodo = new Nodo(valor);

            if (primero == null)
            {
                primero = nuevoNodo;
            }
            else
            {
                Nodo actual = primero;
                while (actual.siguiente != null)
                {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevoNodo;
            }

            numeroDatos++;
        }
    }

    public boolean hayEspacioDisponible()
    {
        return numeroDatos < capacidadMaxima;
    }

    public int obtenerValor(int posicion)
    {
        assert posicion >= 0 && posicion < numeroDatos : "La posición está fuera del rango de elementos guardados.";

        Nodo actual = primero;
        for (int i = 0; i < posicion; i++)
        {
            actual = actual.siguiente;
        }

        return actual.valor;
    }

    public void eliminar(int posicion)
    {
        assert posicion >= 0 && posicion < numeroDatos : "La posición está fuera del rango de elementos guardados.";

        if (posicion == 0)
        {
            primero = primero.siguiente;
        }
        else
        {
            Nodo actual = primero;
            for (int i = 0; i < posicion - 1; i++)
            {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
        }

        numeroDatos--;
    }

    public int getNumeroDatos()
    {
        return this.numeroDatos;
    }

    public void imprimirArray()
    {
        System.out.print("[ ");
        for (int i = 0; i < this.numeroDatos; i++)
        {
            System.out.print(this.obtenerValor(i));
            if (i < this.numeroDatos - 1)
            {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}