Cola cola = new Cola();

cola.encolar(1);
cola.encolar(2);
cola.encolar(3);

assert cola.desencolar() == 1;

cola.encolar(4);

assert cola.desencolar() == 2;
assert cola.desencolar() == 3;
