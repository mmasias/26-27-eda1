import random
from persona import Persona


class SimulacionFila:
    DURACION_MINUTOS = 120  # 2 horas
    MINUTO_ACTIVACION_EXTENDIDA = 20
    LIMITE_RECOMENDADO_FILA = 30
    UMBRAL_AVISO_PARLANTES = 25

    PROB_LLEGADA_NORMAL = 0.6
    PROB_ABRIR_CAJA = 0.4
    PROB_ABURRIRSE = 0.3

    def __init__(self):
        self.fila: list[Persona] = []
        self.contador_ids = 0

        self.total_atendidos = 0
        self.total_aburridos = 0
        self.total_desistieron_por_larga = 0
        self.total_colados_preferentes = 0
        self.total_colados_ilicitos = 0
        self.total_compras_entregadas = 0

    def ejecutar(self):
        print("=== INICIO SIMULACIÓN LA FILA EN PYTHON (2 HORAS) ===\n")

        for minuto in range(1, self.DURACION_MINUTOS + 1):

            if random.random() < self.PROB_ABRIR_CAJA:
                self._atender_al_frente()

            if random.random() < self.PROB_LLEGADA_NORMAL:
                es_preferente = (minuto >= self.MINUTO_ACTIVACION_EXTENDIDA) and (random.random() < 0.15)
                self._insertar_por_llegada(minuto, es_preferente)

            if minuto >= self.MINUTO_ACTIVACION_EXTENDIDA:

                if random.random() < 0.10:
                    self._insertar_preferente(minuto)

                if random.random() < 0.10:
                    self._colarse_detras_de_conocido(minuto)

                if random.random() < 0.05:
                    self._entregar_compras_a_otro()

                if minuto % 5 == 0:
                    self._procesar_aburrimiento(minuto)

            if minuto % 15 == 0 and len(self.fila) > self.UMBRAL_AVISO_PARLANTES:
                print(f"[PARLANTES Min {minuto}]: «Pasen por esta caja en orden de fila»")
                for _ in range(min(5, len(self.fila))):
                    self._atender_al_frente()

            longitud_m = len(self.fila)
            print(f"Minuto {minuto} | Longitud de la cola: {longitud_m} m ({longitud_m} personas)")

        self._imprimir_reporte_final()

    def _atender_al_frente(self):
        if self.fila:
            self.fila.pop(0)
            self.total_atendidos += 1

    def _evaluar_desistencia_por_fila_larga(self) -> bool:
        if len(self.fila) >= self.LIMITE_RECOMENDADO_FILA and random.random() < 0.5:
            self.total_desistieron_por_larga += 1
            return True
        return False

    def _insertar_por_llegada(self, minuto: int, es_preferente: bool):
        if self._evaluar_desistencia_por_fila_larga():
            return

        if es_preferente:
            self._insertar_preferente(minuto)
            return

        self.contador_ids += 1
        nueva = Persona(self.contador_ids, minuto, preferente=False)
        self.fila.append(nueva)

    def _insertar_preferente(self, minuto: int):
        if self._evaluar_desistencia_por_fila_larga():
            return

        self.contador_ids += 1
        nueva = Persona(self.contador_ids, minuto, preferente=True)
        self.total_colados_preferentes += 1

        pos_insercion = 0
        for i, persona in enumerate(self.fila):
            if persona.preferente:
                pos_insercion = i + 1
            else:
                break

        self.fila.insert(pos_insercion, nueva)

    def _colarse_detras_de_conocido(self, minuto: int):
        if not self.fila or self._evaluar_desistencia_por_fila_larga():
            return

        pos_conocido = random.randint(0, len(self.fila) - 1)
        self.contador_ids += 1
        colado = Persona(self.contador_ids, minuto, preferente=False)

        self.fila.insert(pos_conocido + 1, colado)
        self.total_colados_ilicitos += 1

    def _entregar_compras_a_otro(self):
        if len(self.fila) > 1:
            pos_abandono = random.randint(0, len(self.fila) - 1)
            self.fila.pop(pos_abandono)
            self.total_compras_entregadas += 1

    def _procesar_aburrimiento(self, minuto_actual: int):
        nueva_fila = []
        for persona in self.fila:
            if persona.calcular_tiempo_espera(minuto_actual) > 8 and random.random() < self.PROB_ABURRIRSE:
                self.total_aburridos += 1
            else:
                nueva_fila.append(persona)
        self.fila = nueva_fila

    def _imprimir_reporte_final(self):
        print("\n=======================================================")
        print("             REPORTE FINAL DE LA SIMULACIÓN            ")
        print("=======================================================")
        print(f" Personas atendidas con éxito:       {self.total_atendidos}")
        print(f" Personas restantes en fila:          {len(self.fila)} ({len(self.fila)} metros)")
        print(f" Personas aburridas que se fueron:    {self.total_aburridos}")
        print(f" Personas que no entraron (cola >30): {self.total_desistieron_por_larga}")
        print(f" Coladas preferentes aceptadas:       {self.total_colados_preferentes}")
        print(f" Coladas ilícitas realizadas:        {self.total_colados_ilicitos}")
        print(f" Entregas de compra a otra persona:   {self.total_compras_entregadas}")
        print("=======================================================")


if __name__ == "__main__":
    simulacion = SimulacionFila()
    simulacion.ejecutar()