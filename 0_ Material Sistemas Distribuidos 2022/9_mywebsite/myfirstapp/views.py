
from django.shortcuts import render
from django.http import HttpResponse
from myfirstapp.models import Estudiante, Carrera
# Create your views here.

def index(request):
    estudiantes = Estudiante.objects.order_by("nombre")
    lista = ""
    for e in estudiantes:
        lista += str(e) + ", "
    return HttpResponse(lista)


def detalles(request, estudiante_id):
    return HttpResponse("Detalles del estudiante %s." % estudiante_id)

def carreras(request, estudiante_id):
    return HttpResponse("Carreras del estudiante %s." % estudiante_id)

def agrega_carrera(request, estudiante_id):
    return HttpResponse("Agregando una carrera a %s." % estudiante_id)
