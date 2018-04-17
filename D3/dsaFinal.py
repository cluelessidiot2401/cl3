import random
import hashlib
def isprime(num):
	num=abs(int(num))
	if num<2:
		return False
	if num==2:
		return True
	if not num & 1:
		return False
	for x in range(3,int(pow(num,0.5))+1,2):
		if num % x == 0:
			return False
	return True
	
def check_tuple_compat(p,q):
	if not isprime(p):
		print('p is not prime')
		return False
	if not isprime(q):
		print ('q is not prime')
		return False
	if (p-1)%q !=0:
		print ('p is not prime modulus of q')
		return False
	return True

def get_g(p,q,h):
	for g in range(1,p):
		if pow(g,q)%p==1 and g==pow(h,((p-1)/q))%p:
			return g
	return -1

if __name__ == "__main__":
	while True:	
		print ('Enter the parameter tuple<p,q> :')
		p=int(input('p: '))
		q=int(input('q: '))

		if not check_tuple_compat(p,q):
			print('Invalid parameter tuple... try again')
		else:
			print("DONE")
			break
	msg=input("Enter the message:")
	m=hashlib.sha1()
	m.update(msg.encode())
	print (m.hexdigest())
	h=int(str(m.hexdigest()),16)
	print ("h: ",h)
	g=get_g(p,q,h)
	print("g: ",g)

	while True:
		x=int(input("Enter value of x (1<x<q):"))
		if x<1 or x>q:
			print('Invalid x!.... Try again')
		else:
			break

	y=pow(g,x)%p
	print ('Public key: ',[p,q,g,y])
	print ('Private key: ',[p,q,g,x])

	#generating signature with private key
	k=random.randint(1,q-1)
	print("random = ",k)
	r=(pow(g,k)%p)%q
	i=0
	while True:
		if (k*i)%q==1:
			break
		i=i+1
	s=i*(h+r*x)

	print("Digital siganture produced: {r,s}: ",[r,s])

	#verifying signature with public key
	w=0
	while True:
		if (s*w)%q==1:
			break
		w=w+1
	u1=(h*w)%q
	u2=(r*w)%q
	v=((pow(g,u1)*pow(y,u2))%p%q)
	print(v)
	print(r)
	if v==r:
		print("Verification succesfull!")
	else:
		print("Verification failed!")