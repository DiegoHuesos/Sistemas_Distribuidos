from django.contrib import admin
from django.urls import path
from . import views

app_name = "myfirstapp"



urlpatterns = [
    path("", views.index,name="index"),
    path("<int:estudiante_id>/", views.detalles,name="detalles"),
    path("<int:estudiante_id>/detalles/", views.detalles,name="detalles"),
    path("<int:estudiante_id>/<int:tipo>/<str:nombre>/agrega_carrera/", views.agrega_carrera,name="agrega_carrera"),
    path("<str:nombre>/<str:apellidos>/<int:edad>/<str:foraneo>/<str:promedio>/agrega_estudiante/", views.agrega_estudiante, name="agrega_estudiante"),    
    path("<int:estudiante_id>/<str:promedio>/edita_estudiante/", views.edita_estudiante,name="edita_estudiante"),
    path("<int:estudiante_id>/borra_estudiante/", views.borra_estudiante,name="borra_estudiante"),
    path("agrega_estudiante_forma", views.agrega_estudiante_forma,name="agrega_estudiante_forma"),    
]
