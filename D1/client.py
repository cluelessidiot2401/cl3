import hashlib, uuid, socket, sha

def calculateHash(password):
	# salt = uuid.uuid4().hex
	# print(salt)
	# password = "hello"
	hashed_password = hashlib.sha1((password ).encode()).hexdigest()
	print(hashed_password)
	return hashed_password

sock = socket.socket()
sock.connect(("127.0.0.1",5000))

print("Enter data to be sent")
data = str(input())
dataHash = calculateHash(data)
dataHash = sha.shaDigest(data)

sock.send(data.encode())
sock.send(dataHash.encode())

print(sock.recv(1024).decode())

sock.close()
