from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, Http404
from .models import Pensionado
from  django.template  import  loader

# Create your views here.

def index(request):
    context = {}
    return render(request,'examen_final/index.html', context )
