from django.db import models

# Create your models here.

class Pensionado(models.Model):
    nombre = models.CharField(max_length=200)
    edad_actual = models.IntegerField(default=0)
    edad_retiro = models.IntegerField(default=0)
    saldo_acumulado = models.FloatField(default=9.99)
    ahorro_mensual = models.FloatField(default=9.99)
    
    Hombre = 1
    Mujer = 2
    OPCIONES_TIPO = (
        (Hombre, 'Hombre'),
        (Mujer, 'Mujer'),
    )
    genero = models.IntegerField(choices=OPCIONES_TIPO, null=True, blank=True)


    def pension_esperada(self):
        return float(self.edad_actual) * float(self.edad_retiro) * self.saldo_acumulado * self.ahorro_mensual 

    def get_genero(self):
        return Pensionado.OPCIONES_TIPO[self.genero-1][1]

    def text(self):
        return str(self)

    def  __str__(self):
        return  "Nombre: " + self.nombre  +" | Edad actual:"+ str(self.edad_actual) + " | Edad de retiro: "+ str(self.edad_retiro) + " | Género: "+ str(self.get_genero()) + " | Saldo acumulado: "+ str(self.saldo_acumulado) + " | Ahorro mensual: "+ str(self.ahorro_mensual) + " | Pensión esperada: " + str(self.pension_esperada())
    