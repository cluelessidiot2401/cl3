import hashlib, uuid, socket

def calculateHash(password):
	# salt = uuid.uuid4().hex
	# print(salt)
	# password = "hello"
	hashed_password = hashlib.sha1((password).encode()).hexdigest()
	# print(hashed_password)
	return hashed_password

sock = socket.socket()
sock.bind(("127.0.0.1",5000))
sock.listen(1)

conn,cladd = sock.accept()
data = conn.recv(1024).decode()
dataHash = conn.recv(1024).decode()
print(data,dataHash)

calculatedHash = calculateHash(data)
output = ""
if ((calculatedHash==dataHash)):
	output = "Data Integrity confirmed"
	print(output)
	conn.send(output.encode())
else:
	output = "Data has been altered"
	print(output)
	conn.send(output.encode())

conn.close()
sock.close()