package unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF extends QuickUnionUF {
	
	//To contain the size of each component so that to help to determine which one will be the subtree 
	//when two trees union
	private int[] sz;
	
	public WeightedQuickUnionUF(int n) {
		super(n);
		sz = new int[n];
		for(int i = 0; i < n; i++)
			sz[i] = 1;
	}
	
	// Using the sz[] array to determine the sizes of two combining trees,
	// and use the smaller size tree as the subtree of the bigger size tree
	@Override
	public void union(int p, int q) {
		validate(p);
		validate(q);
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		if(pRoot == qRoot) return;
		if(sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
		count--;
			
	}
	
	public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
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
