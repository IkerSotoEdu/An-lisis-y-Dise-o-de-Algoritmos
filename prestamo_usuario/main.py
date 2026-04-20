from datetime import datetime
from typing import List, Optional


class Prestamo:
    def __init__(self, copia, usuario, fecha_inicio: datetime, fecha_vencimiento: datetime):
        self.copia = copia
        self.usuario = usuario
        self.fecha_inicio = fecha_inicio
        self.fecha_vencimiento = fecha_vencimiento
        self.fecha_devolucion: Optional[datetime] = None

    def ejecutar_prestamo(self) -> bool:
        if self.copia.disponible and self.usuario.tiene_cupo():
            self.copia.prestar()
            self.usuario.registrar_prestamo(self)
            return True
        return False

    def finalizar_devolucion(self) -> None:
        self.fecha_devolucion = datetime.now()
        self.copia.devolver()
        self.usuario.quitar_prestamo(self)


class Usuario:
    def __init__(self, idUsuario: int, nombre: str, limite_prestamos: int):
        self.idUsuario = idUsuario
        self.nombre = nombre
        self.limite_prestamos = limite_prestamos
        self.prestamos_activos: List[Prestamo] = []

    def tiene_cupo(self) -> bool:
        return len(self.prestamos_activos) < self.limite_prestamos

    def registrar_prestamo(self, prestamo: Prestamo) -> None:
        self.prestamos_activos.append(prestamo)

    def quitar_prestamo(self, prestamo: Prestamo) -> None:
        if prestamo in self.prestamos_activos:
            self.prestamos_activos.remove(prestamo)