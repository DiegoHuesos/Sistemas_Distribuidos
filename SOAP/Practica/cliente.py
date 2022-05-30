from suds.client import Client

client = Client("http://localhost:8000/?wsdl")

result1 = client.service.simulated_annealing("Hola", 4)

result2 = client.service.genetic_algorithm("Hola", 3, True)

result3 = client.service.ant_colony_optimization()

result4 = client.service.particle_swarm_optimization("Hola", 4)

#diccionario = Client.dict(result)

print("RESULT 1: \n" + str(result1) + "\n\n")
print("RESULT 2: \n" + str(result2) + "\n\n")
print("RESULT 3: \n" + str(result3) + "\n\n")
print("RESULT 4: \n" + str(result4) + "\n\n")
