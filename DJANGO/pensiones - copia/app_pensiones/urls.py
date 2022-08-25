from django.contrib import admin
from django.urls import path
from . import views

app_name = "app_pensiones"



urlpatterns = [
    path('', views.index,name="index"),
    path('agrega_pensionado', views.agrega_pensionado,name="agrega_pensionado"),
    path('pensionados', views.pensionados,name="pensionados"),
]