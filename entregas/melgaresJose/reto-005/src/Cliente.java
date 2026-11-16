class Cliente {
    public static void main(String[] args) {
        PilaEnCola laPilaCola = new PilaEnCola();
        laPilaCola.apilar(1);
        laPilaCola.apilar(2);
        laPilaCola.apilar(3);
        System.out.println("Desapilamos " + laPilaCola.desapilar());
        laPilaCola.apilar(4);
        System.out.println("Desapilamos " + laPilaCola.desapilar());
        System.out.println("Desapilamos " + laPilaCola.desapilar());
    }
}