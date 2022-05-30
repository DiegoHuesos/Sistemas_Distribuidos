import requests


respGet = requests.get("http://localhost:81/", params={"usuario":"Pedro", "contrasena": "1q2w3e4"})

print("respGet: " + str(respGet.content) )



respPut = requests.put("http://localhost:81/registro/chui/1234")

print("respPut: " + str(respPut.content) )