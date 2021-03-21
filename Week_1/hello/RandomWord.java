/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double num = 1.0;
        String champion = "";
        String newWord = "";
        while (!StdIn.isEmpty()) {
            newWord = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / num)) {
                champion = newWord;

            }
            num++;


        }
        StdOut.println(champion);
    }
}

