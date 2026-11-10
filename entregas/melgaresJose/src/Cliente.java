class Cliente {
    public static void main(String[] args) {
        Cola laCola = new Cola();
        laCola.encolar(1);
        laCola.encolar(2);
        laCola.encolar(3);
        System.out.println("Desencolamos " + laCola.desencolar());
        laCola.encolar(4);
        System.out.println("Desencolamos " + laCola.desencolar());
        System.out.println("Desencolamos " + laCola.desencolar());
    }
}