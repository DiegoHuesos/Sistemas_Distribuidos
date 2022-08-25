from http import client
from pyexpat import model
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, Http404
from .models import Cliente, Auto
from  django.template  import  loader

# Create your views here.

def index(request):
    context  =  {}
    return render(request,'sistema_cotizaciones/index.html', context )


def agrega_cliente(request):
    genero = request.POST.get("genero")
    if(genero == "masculino"):
        genero = 0
    else:
        genero = 1

    cliente = Cliente(	    nombre= request.POST.get("nombre"), 
                            apellidos=request.POST.get("apellidos") , 
                            genero = genero, 
                            edad=int(request.POST.get("edad")), 
                            estado=request.POST.get("estado"),
                    )

    cliente.save()
    
    return render(request, "sistema_cotizaciones/auto.html", {"cliente": cliente})


def agrega_auto(request):
    auto = Auto(	    marca= request.POST.get("marca"), 
                        placas=request.POST.get("placas") , 
                        modelo=int(request.POST.get("modelo"))
                )

    auto.save()

    cliente = Cliente.objects.get(id= int( request.POST.get("cliente") ) );
    cliente.costo = str(auto.costo_seguro())
    cliente.save()

    context = { 
        "costo" : str(auto.costo_seguro()),
        "listaClientes": Cliente.objects.order_by("nombre"),
         }
    
    return render(request, "sistema_cotizaciones/cotizacion.html", {"context": context})
