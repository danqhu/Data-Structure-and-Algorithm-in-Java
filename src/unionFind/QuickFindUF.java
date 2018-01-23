package unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* This is the impletation of QuickFind algorithm of Union-Find
 * 
 * */
public class QuickFindUF {
	private int[] id;
	private int count;
	
	/**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own 
     * component.
     *
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
	public QuickFindUF(int n) {
		id = new int[n];
		count = n;
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	// return the number of components
	public int count() {
		return count;
	}
	
	// return the component identifier for the component p if p is valid
	public int find(int p) {
		validate(p);
		return id[p];
	}
	
	//To check if p is a valid component
	private void validate(int p) {
		int n = id.length;
		if(p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
		}
	}
	
	//To check if the two sites are connected
	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return id[p] == id[q];
	}
	
	//To union two sites
	public void union(int p, int q) {
		validate(p);
		validate(q);
		int pID = id[p];
		int qID = id[q];
		
		if(pID == qID) return;
		
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pID) id[i] = qID;
		count--;
		}
	}
	
	public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
