from bitstring import BitArray
import math
from flask import Flask, request, render_template
app = Flask(__name__)

def booths(num1,num2,len):
	totalLength = len+len+1
	mulArray = BitArray(int=num1,length=totalLength)
	compMulArray = BitArray(int=-num1,length=totalLength)

	add = mulArray << (len+1)
	sub = compMulArray << (len+1)

	result = BitArray(int=num2,length = len)
	result.prepend(BitArray(int=0,length=len))
	result = result << (1)

	for i in range(len):
		if result[-2:] == '0b01':
			result = BitArray(int=result.int+add.int,length=totalLength)
		elif result[-2:] == '0b10':
			result = BitArray(int=result.int+sub.int,length=totalLength)
		result = BitArray(int = (result.int >> (1)),length = result.len)	

	result = result[:-1]
	return result.bin,result.int


@app.route('/')
def init():
	return render_template("index.html")

@app.route('/',methods=['POST'])
def multiplyPage():
	num1 = int(request.form['num1'])
	num2 = int(request.form['num2'])
	l1 = max(num1,num2)
	l1 = int(math.ceil(math.log(l1,2))+2)
	# l2 = math.ceil(math.log(num2,2))
	print(l1)
	n,m = booths(num1,num2,l1)
	return "Answer in binary is "+str(n)+" ,<br>Answer in integer is "+str(m)

if __name__ == "__main__":
	app.run('localhost',debug=True)