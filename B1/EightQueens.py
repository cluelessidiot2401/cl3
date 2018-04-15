import json
import unittest

def readAndExecute(filename):

	with open(filename) as f:
		pos = json.load(f)
	# print(pos)
	return EightQueens(pos)


def EightQueens(pos):
	
	data = [-1] * 8

	pos = pos['start'] - 1
	# print(pos)

	if pos>=0 and pos<=7:
		data[0] = pos

		if solve(data,1):
			writeAndPrint(data)
			return True
		else:
			print("Not Solvable")
			return False
	else:
		print("start = "+str(pos))
		print("Invalid Input")
		return False

def solve(data,i):

	if i == 8 :
		return True

	for j in range(0,8):
		if isSafeHere(data,i,j):
			data[i] = j
			if solve(data,i+1) == False:
				data[i] = -1
			else:
				return True
		if j==7 and data[i]==-1:
			return False

def isSafeHere(data,row,col):
	for i in range(0,row):
		if (data[i] == col) or (abs(data[i]-col) == abs(i-row)):
			return False
	return True


def writeAndPrint(data):
	print(data)
	board = [ ['_']*8 for __ in range(0,8) ]
	for i in range(0,8):
		board[i][data[i]]='Q'

	for i in range(0,8):
		for j in range(0,8):
			print(board[i][j]+" ",end='')
		print("")
	print("")

class TestClass(unittest.TestCase):
	def test1(self):
		self.assertEqual(readAndExecute("input.json"),True)
	def test2(self):
		self.assertEqual(readAndExecute("input2.json"),False)
	def test3(self):
		self.assertEqual(readAndExecute("input3.json"),False)

if __name__ == "__main__":
	# readAndExecute("input.json")
	unittest.main()