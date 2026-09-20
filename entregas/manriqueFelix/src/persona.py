class Persona:

    def __init__(self, id_persona: int, minuto_llegada: int, preferente: bool = False):
        self.id = id_persona
        self.minuto_llegada = minuto_llegada
        self.preferente = preferente

    def calcular_tiempo_espera(self, minuto_actual: int) -> int:
        return minuto_actual - self.minuto_llegada

    def __repr__(self) -> str:
        pref = " [Preferente]" if self.preferente else ""
        return f"Persona #{self.id}{pref}"