from django.db import models

class Estudiante(models.Model):
    nombre = models.CharField(max_length=200)
    apellidos = models.CharField(max_length=200)
    edad = models.IntegerField(default=0)
    promedio = models.FloatField(default=9.99)
    foraneo = models.BooleanField(default=False)
    
    def  __str__(self):
        return  self.nombre  +"  "+self.apellidos
    def  aspira_a_beca(self):
        return  self.promedio  >=9



class Carrera(models.Model):
    LICENCIATURA = 1
    INGENIERIA = 2
    OPCIONES_TIPO = (
        (LICENCIATURA, 'Licenciatura'),
        (INGENIERIA, 'Ingenieria'),
    )
    estudiante = models.ForeignKey(Estudiante, on_delete=models.CASCADE)
    tipo = models.IntegerField(choices=OPCIONES_TIPO, null=True, blank=True)
    nombre = models.CharField(max_length=200)

    def __str__(self):
        return str(self.tipo) +" "+self.nombre
    
    def get_tipo(self):
        return Carrera.OPCIONES_TIPO[self.tipo-1][1]

