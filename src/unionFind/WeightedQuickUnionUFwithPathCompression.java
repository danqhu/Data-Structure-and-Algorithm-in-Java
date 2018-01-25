package unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUFwithPathCompression extends WeightedQuickUnionUF {

	public WeightedQuickUnionUFwithPathCompression(int n) {
		super(n);
	}
	
	//Using the path compression technique to assign the parent root to the node we are finding
	//each time we do the findRoot operation(But not the final root, be careful! with many many times findRoot operation, the tree will be flatted almostly)
	@Override
	protected int findRoot(int p) {
		while(p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
		}
		
		return p;
	}
	
	public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUFwithPathCompression uf = new WeightedQuickUnionUFwithPathCompression(n);
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
