from tokenize import String
from typing import Iterable
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from wsgiref.simple_server import make_server
from spyne import Application, ServiceBase, rpc, Integer, Float, Boolean, Unicode, Iterable


class Servicio(ServiceBase):
    @rpc(Unicode, Integer, _returns=(Integer, Float) )
    def simulated_annealing(ctx, s, f):
        print("simulated_annealing")
        return (3, 5.0)
    
    @rpc(Unicode, Integer, Boolean, _returns=Float)
    def genetic_algorithm(ctx, s, i, b):
        print("genetic_algorithm")
        return 0.7

    @rpc( _returns=Iterable(Unicode))
    def ant_colony_optimization(ctx):
        print("ant_colony_optimization")
        lstStr = ["ant_colony_optimization", "Ohhh yeah"]
        return lstStr

    @rpc(Unicode, Integer, _returns=(Integer, Float) )
    def particle_swarm_optimization(ctx, s, i):
        print("particle_swarm_optimization")
        return (6, 5.9)

	

    

application = Application([Servicio],
                          	"localhost",
                         	in_protocol=Soap11(),
                         	out_protocol=Soap11())

wsgi_app = WsgiApplication(application, 8000)

server = make_server("127.0.0.1", 8000, wsgi_app)

server.serve_forever()
 
