package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemset;
import ca.pfv.spmf.tools.MemoryLogger;
import pascal.AlgoPASCAL;
//import ca.pfv.spmf.algorithms.frequentpatterns.pascal.ItemsetPascal;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.aprioriTID.AlgoAprioriTID;
import pascal.ItemsetPascal;
import ultils.Constant;
import ca.pfv.spmf.*;


public class AlgorithmsLogic {
//	public AssocRules generateRulesByFPGrowth(String input_file, double min_sup, double min_conf){
//		AssocRules rules = null;
//		
//		AlgoFPGrowth fpGrowth = new AlgoFPGrowth();
//		try {
//			Itemsets patterns = fpGrowth.runAlgorithm(input_file, null, min_sup);
//			int databaseSize = fpGrowth.getDatabaseSize();
//		    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
//		    rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, min_conf);
//		    System.out.println(rules.toString());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return rules;
//	}
//	
//	public AssocRules generateRulesByApriori(String input_file, double min_sup, double min_conf){
//		AssocRules rules = null;
//		AlgoApriori apri = new AlgoApriori();
//		try {
//			Itemsets patterns = apri.runAlgorithm(min_sup, input_file , null );
//			int databaseSize = apri.getDatabaseSize();
//		    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
//		    rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, min_conf);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return rules;
//	}
	
	public AssocRules generateRulesByPascal(String input_file, double min_sup, double min_conf){
		AssocRules rules = null;
		AlgoPASCAL pascal = new AlgoPASCAL();
		try {
			Itemsets patterns = pascal.runAlgorithm(min_sup, input_file, null);
			int databaseSize = 47;
		    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
		    rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, min_conf);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rules;
	}
	
	
//	public AssocRules generateRulesByPascal(String input_file, double min_sup, double min_conf){
//		AssocRules rules = null;
//		AlgoPASCAL pascal = new AlgoPASCAL();
//		long startTimestamp;
//		long endTimestamp;
//		try {
//			startTimestamp = System.currentTimeMillis();
//	        MemoryLogger.getInstance().reset();
//			double minsup = 0.01;
//			String input = "/home/tuankun/workspace/ElectronicsStore/product_inputx.txt";
//			String output = "/home/tuankun/workspace/ElectronicsStore/result.txt";
//			pascal.runAlgorithm(min_sup, input_file, output);
//			List<Itemset> level = pascal.convertToItemset(pascal.level);
//			List<List<Itemset>> list_level = new ArrayList<>();
//			list_level.add(level);
//			int databaseSize = 47;
//			MemoryLogger.getInstance().checkMemory();
//			endTimestamp = System.currentTimeMillis();
//		    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
//		    rules = algoAgrawal.runAlgorithm((Itemsets) list_level, null, databaseSize, min_conf);
//		} catch (FileNotFoundException e) {
//			System.out.println(1);
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println(2);
//			e.printStackTrace();
//		}
//		
//		return rules;
//	}
	
	
//	public AssocRules generateRulesByPascal(String input_file, double min_sup, double min_conf){
//		long startTimestamp;  // start time
//        long endTimestamp;
////        static double threshold = 225000;
//        int databaseSize = 43;
//        // record start time
//        
//        
//        AssocRules rules = null;
//		try {
//			startTimestamp = System.currentTimeMillis();
//	        MemoryLogger.getInstance().reset();
//	        AlgoPASCAL pascal = new AlgoPASCAL();
//	        String file = "/home/tuankun/workspace/ElectronicsStore/product_inputx";
//			pascal.runAlgorithm(0.01, file , "result.txt");
//	        // check the memory usage
//	        MemoryLogger.getInstance().checkMemory();
//	        // save endtime
//	        endTimestamp = System.currentTimeMillis();
//	        System.out.println("=============  PASCAL  =============");
//	        System.out.println(" Transactions count from database : " + pascal.transactionCount);
//	        System.out.println(" Frequent itemsets count : " + pascal.itemsetCount);
//	        System.out.println(" Maximum memory usage : " + MemoryLogger.getInstance().getMaxMemory() + " mb");
//	        System.out.println(" Total time ~ " + (endTimestamp - startTimestamp) + " ms");
//	        System.out.println("===================================================");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rules;
//	
//	}
	
	public static void main(String []args) {
		AlgorithmsLogic algo = new AlgorithmsLogic();
//			algo.generateRulesByFPGrowth(Constant.PRODUCT_INPUT_FILE, 0.12, 0.33);
		algo.generateRulesByPascal(Constant.PRODUCT_INPUT_FILE, 0.12, 0.33);
//		algo.generateRulesByApriori(Constant.PRODUCT_INPUT_FILE, 0.12, 0.33);
	}
}
