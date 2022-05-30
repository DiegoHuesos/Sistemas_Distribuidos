from suds.client import Client

client = Client("http://localhost:8000/?wsdl")

result = client.service.suma(3, 4)

#diccionario = Client.dict(result)

print(result)
