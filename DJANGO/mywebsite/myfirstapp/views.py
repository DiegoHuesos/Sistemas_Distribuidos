from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, Http404
from  .models  import  Estudiante
from  .models import Carrera
from  django.template  import  loader




# Create your views here.

#def index(request):
#    return HttpResponse("Holaaa")

'''
def  index(request):
    estudiantes  =  Estudiante.objects.order_by('nombre')
    lista  =  ""
    for  e  in  estudiantes:
            lista  +=  e.__str__()+",  "
    return  HttpResponse(lista)
'''

def  index(request):
    estudiantes  =  Estudiante.objects.order_by('nombre')
    #template  =  loader.get_template('myfirstapp/index.html')
    context  =  {
            'estudiantes':  estudiantes,
            'cualquiera': "Hola cualquiera"
    }
    #return  HttpResponse(template.render(context,  request))
    return render(request,'myfirstapp/index.html', context )



def detalles(request, estudiante_id):
    #estudiante = Estudiante.objects.get(pk=estudiante_id)
    #return render(request, "myfirstapp/detalles.html", {"estudiante": estudiante})

    estudiante = get_object_or_404(Estudiante, pk=estudiante_id)
    return render(request, "myfirstapp/detalles.html", {"estudiante": estudiante})

    


def carreras(request, estudiante_id):
  return HttpResponse("Carreras del estudiante %s." % estudiante_id)

def agrega_carrera(request, estudiante_id):
  return HttpResponse("Agregando una carrera a %s." % estudiante_id)


def agrega_carrera(request, estudiante_id, tipo, nombre):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.carrera_set.create(tipo = int(tipo), nombre=nombre)
    return HttpResponse("Carrera agregada al estudiante %s" % estudiante_id)


def agrega_estudiante(request, nombre, apellidos, edad, foraneo, promedio):
    foraneo = foraneo.lower() == "true"
    estudiante = Estudiante(	nombre=nombre, 
				apellidos=apellidos, 
				edad=int(edad), 
				foraneo=foraneo, 
				promedio=float(promedio))
    estudiante.save()
    return HttpResponse("Estudiante %s agregado exitósamente" % estudiante.id)

def edita_estudiante(request, estudiante_id, promedio):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.promedio = float(promedio)
    estudiante.save()    
    return HttpResponse("El promedio del estudiante %s se ha actualizado exitósamente" % estudiante.id)

def agrega_estudiante_forma(request):
    nombre = request.POST.get("nombre")
    apellidos = request.POST.get("apellidos")
    edad = int(request.POST.get("edad"))
    if "foraneo" in request.POST:  #validando que hayan marcado el checkbox de foraneo
        foraneo = True
    else:
        foraneo = False
    promedio = float(request.POST.get("promedio"))
    estudiante = Estudiante(	nombre=nombre, 
				apellidos=apellidos, 
				edad=int(edad), 
				foraneo=foraneo, 
				promedio=float(promedio))
    estudiante.save()
    return HttpResponse("Estudiante %s agregado exitósamente desde la forma" % estudiante.id)

def borra_estudiante(request, estudiante_id):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.delete()
    return HttpResponse("Estudiante %s borrado exitósamente" % estudiante_id)
