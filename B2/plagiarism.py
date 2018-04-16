from flask import Flask, request, render_template
app = Flask(__name__)

def checkForPlagiarism(text):
	textData = text.split(".")
	toCheck = [x.strip() for x in textData if x.strip()!="" ]
	# toCheck = textData
	# print(toCheck)
	data = ""
	toCheckAgainst = []
	with open("data.txt","r") as openFile:
		data += openFile.read()
	toCheckData = data.split(".")
	toCheckAgainst = [x.strip() for x in toCheckData if x.strip()!="" ]

	score = 0
	total = len(toCheck)

	for x in toCheckAgainst:
		for y in toCheck:
			if x==y:
				score = score + 1
	print(score)
	print(total)
	return float(score)*100/float(total)

@app.route('/')
def init():
	return render_template("index.html")

@app.route('/',methods=['POST'])
def check():
	text1 = str(request.form['inp'])
	print(text1)
	return "Plagiarism % = "+str(checkForPlagiarism(text1))

if __name__ == "__main__":
	app.run('localhost',debug = True)
