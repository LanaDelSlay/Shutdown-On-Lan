from http.server import HTTPServer, BaseHTTPRequestHandler

class Serv(BaseHTTPRequestHandler):
	def do_GET(self):
	    if self.path == '/':
	        self.path = '/index.html'
	    try:
	        file_to_open = open(self.path[1:]).read()
	        self.send_response(200)
	    except:
	        file_to_open = "File not found"
	        self.send_response(404)
	    self.end_headers()
	    self.wfile.write(bytes(file_to_open.encode("utf-8")))


httpd = HTTPServer(('0.0.0.0',8750),Serv)
httpd.serve_forever()