package com.company;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int N = StdIn.readInt();
        UnionFind uf = new UnionFind(N);
        while(!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(!uf.connected(p,q)){
                uf.union(p,q);
                StdOut.println(p + " " +q );

            }
        }
        StdOut.println(uf.count());

    }
}
