import java.util.Arrays;

public class ThreadQuickSort implements Runnable {
	
	public static int[] a;
	public int low;
	public int high;
	
	public static String staticToString() {
		return Arrays.toString(a);
	}
	
	public ThreadQuickSort(int l, int h) {
		low = l;
		high = h;
	}
	
	public static void sort(int l, int h) {
		try {
			Thread t = new Thread(new ThreadQuickSort(l,h));
			t.start();
			t.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sort() {
		sort(0,a.length-1);
	}
	
	public static void swap(int i,int j) {
		System.out.println(a[i]+" -> "+a[j]+"\tTop");
		a[i] = a[i] ^ a[j];
		System.out.println(a[i]+" -> "+a[j]);
		a[j] =  a[i] ^ a[j];
		System.out.println(a[i]+" -> "+a[j]);
		a[i] =  a[i] ^ a[j];
		System.out.println(a[i]+" -> "+a[j]+"\tDone");
	}
	
	public static void swapWithTemp(int i,int j) {
//		System.out.println(a[i]+" -> "+a[j]);
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
//		System.out.println(a[i]+" -> "+a[j]+"\tDone");
	}

	public static int partition(int l, int h) {
		
		int pivot = a[h];
		
		 
	    int i = (l - 1);  // Index of smaller element

	    for (int j = l; j <= h - 1; j++)
	    {
	        // If current element is smaller than or
	        // equal to pivot
	        if (a[j] <= pivot)
	        {
	            i++;    // increment index of smaller element
//	            swap(i,j);
	            swapWithTemp(i, j);
	        }
	    }
//	    swap(i+1,h);
	    swapWithTemp(i+1, h);

	    return (i + 1);
	}
	
	public void QS() {
		if(low < high) {
			int pi = partition(low, high);
			sort(low,pi-1);
			sort(pi+1,high);
		}
	}
	
	@Override
	public void run() {
		QS();
	}

}
