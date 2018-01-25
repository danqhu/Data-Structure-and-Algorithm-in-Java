package unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/* This is the impletation of QuickFind algorithm of Union-Find
 * 
 * COST MODEL
 * algorithm			initialize			union			find
 * quick-find		N					N^				N
 * ^ : it depends on the cost of finding root
 * */
public class QuickUnionUF {

	protected int[] id;
	protected int count;
	
	/**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own 
     * component.
     *
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
	public QuickUnionUF(int n) {
		id = new int[n];
		count = n;
		for(int i = 0; i < n; i++)
			id[i] = i;
	}
	
	
	
	// return the number of components
	public int count() {
		return count;
	}
	
	
	
	//To check if the input value is valid for the accessing of the array id
	protected void validate(int p) {
		int n = id.length;
		if(p < 0 || p >= n)
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
	}
	
	// return the root value of p
	protected int findRoot(int p) {
		validate(p);
		while(id[p] != p) {
			p = id[p];
		}
		
		return p;
	}
	
	// return if p and q are in the same component
	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return findRoot(p) == findRoot(q);
	}
	
	public void union(int p, int q) {
		validate(p);
		validate(q);
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		if (pRoot == qRoot) return;
		id[pRoot] = qRoot;
		count--;
	}
	
	
	public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            String head = "";
            String content = "";
	    		for(int i = 0; i < uf.id.length; i++) {
	    			head += i;
	    			head += " ";
	    			content += uf.id[i];
	    			content += " ";
	    		}
	    		 StdOut.println(head);
	    		 StdOut.println(content);
        }
        StdOut.println(uf.count() + " components");
    }
	
	
	
	
}
