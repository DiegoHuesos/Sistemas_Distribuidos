from django.db import models

# Create your models here.
class Cliente(models.Model):
    nombre = models.CharField(max_length=200)
    apellidos = models.CharField(max_length=200)

    edad = models.IntegerField(default=0)

    estado = models.CharField(max_length=200)
    
    Hombre = 1
    Mujer = 2
    OPCIONES_TIPO = (
        (Hombre, 'Hombre'),
        (Mujer, 'Mujer'),
    )
    genero = models.IntegerField(choices=OPCIONES_TIPO, null=True, blank=True)

    costo = models.CharField(max_length=200)

    #def pension_esperada(self):
    #    return float(self.edad_actual) * float(self.edad_retiro) * self.saldo_acumulado * self.ahorro_mensual 

    def get_genero(self):
        return Cliente.OPCIONES_TIPO[self.genero-1][1]

    def text(self):
        return str(self)

    def  __str__(self):
        return  "Nombre: " + self.nombre  +" | Edad:"+ str(self.edad) +  " | Género: "+ str(self.get_genero())     




class Auto(models.Model):
    marca = models.CharField(max_length=200)
    placas = models.CharField(max_length=200)
    modelo = models.IntegerField(default=0)

    def costo_seguro(self):
        if self.modelo < 2000:
            return "$3000.50"
        else:
            return "6001.75"

    def text(self):
        return str(self)

    def  __str__(self):
        return  "Marca: " + self.marca  +" | Mpdelo:"+ str(self.modelo) +  " | Placas: "+ str(self.placas)    