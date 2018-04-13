package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.ProductRule;
import entities.SubCategoryRule;

public class SubCategoryRuleDao extends DbManager{
	public void insertRule(int sub_category_id1, int sub_category_id2, int sup_count, double conf){
		String sql = "insert into sub_category_rule (sub_category_id1, sub_category_id2, sup_count, conf) values(?, ?, ?, ?)";
		PreparedStatement pstm;
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, sub_category_id1);
				pstm.setInt(2, sub_category_id2);
				pstm.setInt(3, sup_count);
				pstm.setDouble(4, conf);
				pstm.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void deleteAllRules(){
		String sql = "truncate table sub_category_rule";
		PreparedStatement pstm;
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public ArrayList<SubCategoryRule> getAllRule(){
		ArrayList<SubCategoryRule> listRule = null;
		SubCategoryRule subCategoryRule;
		String sql = "select * from sub_category_rule";
		PreparedStatement pstm;
		ResultSet rs;
		openConnection();
		if(connection != null) {
			try {
				listRule = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()) {
					subCategoryRule = new SubCategoryRule();
					subCategoryRule.setSubCategoryId1(rs.getInt("sub_category_id1"));
					subCategoryRule.setSubCategoryId2(rs.getInt("sub_category_id2"));
					subCategoryRule.setSupCount(rs.getInt("sup_count"));
					subCategoryRule.setConf(rs.getDouble("conf"));
					listRule.add(subCategoryRule);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listRule;
	}
	public ArrayList<Integer> getRuleList(int subCategoryRuleId) {
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "select * from sub_category_rule where sub_category_id1 = ?";
		PreparedStatement pstm;
		ResultSet rs;
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, subCategoryRuleId);
				rs = pstm.executeQuery();
				while(rs.next()){
					list.add(rs.getInt("sub_category_id2"));	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}
	public static void main(String []args) {
		SubCategoryRuleDao dao = new SubCategoryRuleDao();
		dao.getRuleList(4);
	}
}
