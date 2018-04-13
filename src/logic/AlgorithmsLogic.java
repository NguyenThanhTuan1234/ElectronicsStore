package logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import ultils.Constant;

public class AlgorithmsLogic {
	public AssocRules generateRulesByFPGrowth(String input_file, double min_sup, double min_conf){
		AssocRules rules = null;
		
		AlgoFPGrowth fpGrowth = new AlgoFPGrowth();
		try {
			Itemsets patterns = fpGrowth.runAlgorithm(input_file, null, min_sup);
			int databaseSize = fpGrowth.getDatabaseSize();
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
	public static void main(String []args) {
		AlgorithmsLogic algo = new AlgorithmsLogic();
		algo.generateRulesByFPGrowth(Constant.PRODUCT_INPUT_FILE, 0.12, 0.33);
	}
}
