package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.ProductRule;

public class ProductRuleDao extends DbManager{
	public ArrayList<Integer> getRule(int product_id, int numberRule) {
		ArrayList<Integer> list = null;
		String sql = "select product_id2 from product_rule WHERE product_id1 = ? ORDER BY sup_count DESC LIMIT ?";
		PreparedStatement pstm;
		ResultSet rs;
		
		openConnection();
		if(connection != null) {
			try {
				list = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, product_id);
				pstm.setInt(2, numberRule);
				rs = pstm.executeQuery();
				while(rs.next()){
					list.add(rs.getInt("product_id2"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
	} 
	public void insertRule(int product_id1, int product_id2, int sup_count, double conf){
		String sql = "insert into product_rule (product_id1, product_id2, sup_count, conf) values(?, ?, ?, ?)";
		PreparedStatement pstm;
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, product_id1);
				pstm.setInt(2, product_id2);
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
		String sql = "truncate table product_rule";
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
	public ArrayList<ProductRule> getAllRule(){
		ArrayList<ProductRule> listRule = null;
		ProductRule productRule;
		String sql = "select * from product_rule";
		PreparedStatement pstm;
		ResultSet rs;
		openConnection();
		if(connection != null) {
			try {
				listRule = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()) {
					productRule = new ProductRule();
					productRule.setProductId1(rs.getInt("product_id1"));
					productRule.setProductId2(rs.getInt("product_id2"));
					productRule.setSupCount(rs.getInt("sup_count"));
					productRule.setConf(rs.getDouble("conf"));
					listRule.add(productRule);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listRule;
	}
	public static void main(String []agrs){
		ProductRuleDao dao = new ProductRuleDao();
		dao.insertRule(1, 4, 123, 0.33333);
		dao.getAllRule();
	}
}
