/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pascal;

import ca.pfv.spmf.tools.MemoryLogger;
import java.io.IOException;

/*
 *
 * @author Administrator
 */
public class Pascal {

    /**
     * @param args the command line arguments
     */
    static double threshold = 225000;
    static String file = "accidents.dat";

    public static void main(String[] args) throws IOException {
        long startTimestamp;  // start time
        long endTimestamp;
        // record start time
        double databaseSize = 340183;
        //49046
        startTimestamp = System.currentTimeMillis();
        MemoryLogger.getInstance().reset();
        AlgoPASCAL pascal = new AlgoPASCAL();
        pascal.runAlgorithm(0.01, file, "result.txt");
        // check the memory usage
        MemoryLogger.getInstance().checkMemory();
        // save endtime
        endTimestamp = System.currentTimeMillis();
        System.out.println("=============  PASCAL  =============");
        System.out.println(" Transactions count from database : " + pascal.transactionCount);
        System.out.println(" Frequent itemsets count : " + pascal.itemsetCount);
        System.out.println(" Maximum memory usage : " + MemoryLogger.getInstance().getMaxMemory() + " mb");
        System.out.println(" Total time ~ " + (endTimestamp - startTimestamp) + " ms");
        System.out.println("===================================================");
    }

}
