from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from wsgiref.simple_server import make_server
from spyne import Application, ServiceBase, rpc, Integer


class Servicio(ServiceBase):
    @rpc(Integer, Integer, _returns=Integer)
    def suma(ctx, a, b):
        print(ctx)
        return a+b

	

    

application = Application([Servicio],
                          	"localhost",
                         	in_protocol=Soap11(),
                         	out_protocol=Soap11())

wsgi_app = WsgiApplication(application, 8000)

server = make_server("127.0.0.1", 8000, wsgi_app)

server.serve_forever()
 
