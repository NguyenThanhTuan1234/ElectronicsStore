package logic;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRule;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import dao.ProductRuleDao;
import dao.SubCategoryRuleDao;

public class SubCategoryRuleLogic {
	public void insertRules(AssocRules rules){
		SubCategoryRuleDao subCategoryRuleDao = new SubCategoryRuleDao();
		subCategoryRuleDao.deleteAllRules();
		int support;
		int item1[], item2[], sub_category_id1, sub_category_id2;
		double conf;
		if(rules != null) {
			for(AssocRule rule : rules.getRules()) {
				support = rule.getAbsoluteSupport();
				conf = rule.getConfidence();
				item1 = rule.getItemset1();
				item2 = rule.getItemset2();
				if(item1.length != 1 || item2.length != 1) break;
				sub_category_id1 = item1[0];
				sub_category_id2 = item2[0];
				subCategoryRuleDao.insertRule(sub_category_id1, sub_category_id2, support, conf);
			}
		}
	}
}
