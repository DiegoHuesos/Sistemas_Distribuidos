from django.contrib import admin
from django.urls import path
from . import views

app_name = "sistema_cotizaciones"



urlpatterns = [
    path('', views.index,name="index"),
    path('agrega_cliente', views.agrega_cliente,name="agrega_cliente"),
    path('agrega_auto', views.agrega_auto,name="agrega_auto"),
]