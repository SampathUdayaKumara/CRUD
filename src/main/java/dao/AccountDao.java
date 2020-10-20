package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBCConnection;
import domain.Account;

public class AccountDao {

		private final String INSERT_QUERY = "INSERT INTO account(ACC_ID, ACC_NO, ACC_TYPE, P_ID, BALANCE) VALUES(?,?,?,?,?)";
		private final String UPDATE_QUERY = "UPDATE account SET BALANCE=? WHERE ACC_ID=?" ;
		private final String SELECT_QUERY = "SELECT ACC_ID, ACC_NO, ACC_TYPE, P_ID, BALANCE FROM account WHERE ACC_ID=? ";
		private final String SELECT_ALL_QUERY = "SELECT * FROM account";
		private final String DELETE_QUERY = "DELETE FROM account WHERE ACC_ID=?";
		private final String DELETE_ALL_QUERY = "DELETE FROM account";
		 
		 public void insertAccount(Account a) throws SQLException {
			 	Connection con=null;
				PreparedStatement ps =null;
				try {
					con = JDBCConnection.getConnection();
						if (con == null) {
							System.out.println("Connection Error");
							return;
						}
					con.setAutoCommit(false);
					ps=con.prepareStatement(INSERT_QUERY);
					ps.setLong(1,a.getAccId());
					ps.setString(2,a.getAccNo());
					ps.setString(3,a.getAccType());
					ps.setLong(4,a.getUserId());
					ps.setDouble(5,a.getBalance());
					ps.execute();
					
					ps.execute();
					System.out.println("Insert Account => "+ps.toString());
					con.commit();
					
				}catch (SQLException e) {
					// TODO: handle exception
					try {
						if (con != null) {
							con.rollback();
						}
					} catch (SQLException e1) {
						// TODO: handle exception
						throw e1;
					}
					throw e;
				}
				finally {
					try {
						JDBCConnection.closePrepaerdStatment(ps);
						JDBCConnection.closeConnection(con);
					} catch (SQLException e2) {
						// TODO: handle exception
						throw e2;
						
					}
				}
				
			}	 
		
		 public void updateAccount(Account a) throws SQLException {
			Connection con=null;
			PreparedStatement ps =null;
			try {
				con = JDBCConnection.getConnection();
				if (con == null) {
					System.out.println("Connection Error");
					return;
				}
				con.setAutoCommit(false);
				ps=con.prepareStatement(UPDATE_QUERY);
				ps.setDouble(1,a.getBalance());
				ps.setLong(2,a.getAccId());
				ps.execute();
				System.out.println("Update Account Balance => "+ps.toString());
				
			} catch (SQLException e) {
				// TODO: handle exception
				try {
					if (con != null) {
						con.rollback();
						throw e;
					}
				} catch (SQLException e1) {
					// TODO: handle exception
					throw e1;
				}
			}finally {
				try {
					JDBCConnection.closePrepaerdStatment(ps);
					JDBCConnection.closeConnection(con);
				} catch (SQLException e1) {
					// TODO: handle exception
					throw e1;
				}
			}
			
	}
	
		 public Account selectAccount(long accId) throws SQLException {
			 	Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Account a = new Account();
				try {
					con = JDBCConnection.getConnection();
					if (con == null) {
						System.out.println("Connection Error");
						return a;
					}
					ps=con.prepareStatement(SELECT_QUERY);
					ps.setLong(1,a.getAccId());
					rs= ps.executeQuery();
					System.out.println("Select Account => "+ps.toString());
					while (rs.next()) {
						Account acc =new Account();
						acc.setAccId(rs.getLong("ACC_ID"));
						acc.setAccNo(rs.getString("ACC_NO"));
						acc.setAccType(rs.getString("ACC_TYPE"));
						acc.setUserId(rs.getLong("P_ID"));
						acc.setBalance(rs.getDouble("BALANCE"));
					}
				} catch (SQLException e) {
					// TODO: handle exception
					throw e;
				}
				
				finally {
					try {
						JDBCConnection.closeResultSet(rs);
						JDBCConnection.closePrepaerdStatment(ps);
						JDBCConnection.closeConnection(con);
					} catch (SQLException e1) {
						// TODO: handle exception
						throw e1;
					}
				}
				return a;
				
			}
	
		 public ArrayList<Account> selectAccount() throws SQLException{
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				ArrayList<Account> a = new ArrayList<Account>();
				
				try {
					con =JDBCConnection.getConnection();
					if (con == null) {
						System.out.println("Connection Error");
						return a;
					}
					ps = con.prepareStatement(SELECT_ALL_QUERY);
					rs = ps.executeQuery();
					System.out.println("retrivePersons => "+ps.toString());
					while (rs.next()) {
						Account acc =new Account();
						acc.setAccId(rs.getLong("ACC_ID"));
						acc.setAccNo(rs.getString("ACC_NO"));
						acc.setAccType(rs.getString("ACC_TYPE"));
						acc.setUserId(rs.getLong("P_ID"));
						acc.setBalance(rs.getDouble("BALANCE"));
						a.add(acc);
					}
				} catch (SQLException e) {
					// TODO: handle exception
					throw e;
				}
				finally {
					try {
						JDBCConnection.closeResultSet(rs);
						JDBCConnection.closePrepaerdStatment(ps);
						JDBCConnection.closeConnection(con);
					} catch (SQLException e1) {
						// TODO: handle exception
						throw e1;
					}
				}
				return a;
		}
	
		 public void deleteAccount(Account a) throws SQLException {
			 Connection con = null;
				PreparedStatement ps = null;
				try {
					con = JDBCConnection.getConnection();
					ps=con.prepareStatement(DELETE_QUERY);
					ps.setLong(1,a.getAccId());
					ps.execute();
					System.out.println("Delete Account => "+ps.toString());
					
				} catch (SQLException e) {
					// TODO: handle exception
					throw e;
				}finally {
					try {
						JDBCConnection.closePrepaerdStatment(ps);
						JDBCConnection.closeConnection(con);
					} catch (SQLException e1) {
						// TODO: handle exception
						throw e1;
					}
				}
				
			}
	
		 public void deleteAllAccount(Account a) throws SQLException {
			 Connection con = null;
				PreparedStatement ps = null;
				try {
					con = JDBCConnection.getConnection();
					if (con == null) {
						System.out.println("Connection Error");
						return;
					}
					ps=con.prepareStatement(DELETE_ALL_QUERY);
					ps.execute();
					System.out.println("Delete All Account records => "+ps.toString());
				} catch (SQLException e) {
					// TODO: handle exception
					throw e;
				}
				finally {
					try {
						JDBCConnection.closePrepaerdStatment(ps);
						JDBCConnection.closeConnection(con);
					} catch (SQLException e1) {
						// TODO: handle exception
						throw e1;
					}
				}	
			}

}
