from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, Http404
from .models import Pensionado
from  django.template  import  loader

# Create your views here.

def index(request):
    context = {}
    return render(request,'app_pensiones/index.html', context )

def pensionados(request):
    pensionados  =  Pensionado.objects.order_by('nombre')
    #template  =  loader.get_template('myfirstapp/index.html')
    context  =  {
            'pensionados':  pensionados
    }
    
    return render(request,'app_pensiones/pensionados.html', context )


def agrega_pensionado(request):
    
    pensionado = Pensionado(	nombre= request.POST.get("nombre"), 
                                edad_actual=int( request.POST.get("edad_actual") ), 
                                edad_retiro=int(request.POST.get("edad_retiro")), 
                                saldo_acumulado=float(request.POST.get("saldo_acumulado")), 
                                ahorro_mensual=float(request.POST.get("ahorro_mensual")),
                                genero = request.POST.get("genero")
                            )
    pensionado.save()
    
    return render(request, "app_pensiones/resultado_simulacion.html", {"pensionado": pensionado})
