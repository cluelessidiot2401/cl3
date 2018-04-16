#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;
#define f(i,n)	for(unsigned int i=0;i<n;++i)
class BinarySearch
{
private:
	vector<int> a;
	string exceptions[2]={"File Not Found","0 size"};

public:

	void readFromFile(string fileName)
	{
		try{
			ifstream inp;
			inp.open(fileName.c_str(),ios_base::in);
			if(!inp)	throw 0;
			unsigned int size;
			inp>>size;
			int temp;
			f(i,size){
				inp>>temp;
				a.push_back(temp);
			}
		}
	    catch (int problem)  {
	        cerr << "Error Occured\t";
	        cerr<<exceptions[problem]<<"\n";
	    }
	}

	unsigned int getSize(){
		return a.size();
	}

	void printArray(){
		cout<<"Array contents are:\n";
		f(i,getSize())	cout<<a[i]<<" ";
		cout<<"\n";
	}

	void input()
	{
		cout<<"Enter size of the array:\n";
		unsigned int size;
		cin>>size;
		cout<<"\nEnter the elements\n";
		int temp;
		f(i,size){
			cin>>temp;
			a.push_back(temp);
		}
	}

	void sortTheArray(){
		try{
			if(getSize()==0)	throw 1;
			sort(a.begin(),a.end());
		}
		catch(int prob){
			cerr<<"Error occured\t";
			cerr<<exceptions[prob]<<"\n";
		}
	}

	int parseInteger(string inp, bool &error){
		try{
			f(i,inp.size()){
				int temp = inp[i] - '0';
				if(temp<0 || temp>9){
					if(!(i==0 && ( inp[i]=='-' || inp[i]=='+')))
					throw -1;
				}
			}
			return std::stoi(inp.c_str());
		}
		catch(...){
			cerr<<"Error occured:\tInvalid Input\n";
			error = true;
			return -1;
		}
	}

	void searchForElement(string inp)
	{
		bool error = false;
		int val = parseInteger(inp,error);
		if(error)	return;
		try{
			if(getSize()==0)	throw 1;

			bool found = false;

			int hi = a.size()-1;
			int lo = 0;

			while(lo<=hi){
				int mid = (lo+hi)/2;
				if(a[mid] == val){
					if(found){
						cout<<val<<" found again at position "<<mid+1<<"\n";
					}
					else{
						cout<<val<<" found at position "<<mid+1<<"\n";
						found = true;
					}
				}
				if(lo == hi)	break;
				if(a[mid]<val)	lo = mid+1;
				else	hi=mid-1;
			}

			if(!found)	cout<<val<<" couldn't be found\n";
		}
		catch(int prob){
			cerr<<"Error occured while searching for "<<val<<'\t';
			cerr<<exceptions[prob]<<"\n";
		}
	}

};

class UnitTests
{
	BinarySearch bs;

public:

	void openFileTest(){
		bs.readFromFile("input");
	}

	void sortTest(){
		bs.sortTheArray();
	}

	void searchTest(){

//		f(i,15)
//			bs.searchForElement(i);
		string inp="";
		cout<<"To exit, type 'exit'\n";
		cout<<"Enter element to search for\n";
		cin>>inp;

		while(inp!="exit"){
			bs.searchForElement(inp);
			cout<<"To exit, type 'exit'\n";
			cout<<"Enter element to search for\n";
			cin>>inp;
		}

	}

	void getContentTest(){
		bs.printArray();
	}

	void runAllTests(){
		openFileTest();
		getContentTest();
		sortTest();
		getContentTest();
		searchTest();		
	}

};

int main()
{
//	BinarySearch bs;
//	bs.readFromFile("input");
//	bs.sortTheArray();
//	bs.searchForElement(4);

	UnitTests ut;
	ut.openFileTest();
	ut.getContentTest();
	ut.sortTest();
	ut.getContentTest();
	ut.searchTest();

	return 0;
}
