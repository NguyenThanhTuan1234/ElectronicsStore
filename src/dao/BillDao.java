package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Bill;
import entities.Cart;

public class BillDao extends DbManager{
	public void insertBill(Bill bill) {
		String sql = "insert into bill (cart_detail, address, phone, account_id, checked) values(?, N?, ?, ? , ?)";
		PreparedStatement pstm;
		
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				int index = 0;
				pstm.setString(++index, bill.getCart().toString());
				pstm.setString(++index, bill.getAddress());
				pstm.setString(++index, bill.getPhone());
				pstm.setInt(++index, bill.getAccountId());
				pstm.setInt(++index, 0);
				pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void importBill(Bill bill) {
		String sql = "insert into bill (cart_detail, address, phone, account_id, checked) values(?, N?, ?, ? , ?)";
		PreparedStatement pstm;
		
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				int index = 0;
				pstm.setString(++index, bill.getCartString());
				pstm.setString(++index, bill.getAddress());
				pstm.setString(++index, bill.getPhone());
				pstm.setInt(++index, bill.getAccountId());
				pstm.setInt(++index, 0);
				pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public ArrayList<Bill> getListBill(int numberTrans) {
		ArrayList<Bill> listBill = 	null;
		PreparedStatement pstm;
		ResultSet rs;
		Bill bill;
		String sql = "select * from bill limit ?";
		openConnection();
		if(connection !=  null) {
			try {
				listBill = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, numberTrans);
				rs = pstm.executeQuery();
				while(rs.next()) {
					bill = new Bill();
					bill.setCartString(rs.getString("cart_detail"));
					bill.setAddress(rs.getString("address"));
					bill.setPhone(rs.getString("phone"));
					bill.setAccountId(rs.getInt("account_id"));
					bill.setTimeStamp(rs.getTimestamp("create_time").toString());
					bill.setChecked(rs.getInt("checked"));
					listBill.add(bill);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listBill;
	}
	public static void main(String []args) {
		BillDao dao = new BillDao();
		Bill bill = new Bill();
		bill.setCart(new Cart());
		bill.setAddress("Kiến Xương");
		bill.setPhone("092812391");
		bill.setAccountId(1);
		dao.insertBill(bill);
	}
}
