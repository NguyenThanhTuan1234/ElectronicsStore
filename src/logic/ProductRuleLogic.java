package logic;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRule;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import dao.ProductRuleDao;

public class ProductRuleLogic {
	public void insertRules(AssocRules rules){
		ProductRuleDao productRuleDao = new ProductRuleDao();
		productRuleDao.deleteAllRules();
		int support;
		int item1[], item2[], product_id1, product_id2;
		double conf;
		if(rules != null) {
			for(AssocRule rule : rules.getRules()) {
				support = rule.getAbsoluteSupport();
				conf = rule.getConfidence();
				item1 = rule.getItemset1();
				item2 = rule.getItemset2();
				if(item1.length != 1 || item2.length != 1) break;
				product_id1 = item1[0];
				product_id2 = item2[0];
				productRuleDao.insertRule(product_id1, product_id2, support, conf);
			}
		}
	}
}
