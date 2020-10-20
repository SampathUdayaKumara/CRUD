package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBCConnection;
import domain.Person;

public class PersonDao implements PersonDaoInterface {
	
	public static final String INSERT_QUERY="INSERT INTO person(ID,FIRST_NAME,LAST_NAME,EMAIL,JOINED_DATE) VALUES (?,?,?,?,?)";
	public static final String UPDATE_QUERY="UPDATE person SET FIRST_NAME=? WHERE ID=?";
	public static final String SELECT_QUERY="SELECT ID,FIRST_NAME,LAST_NAME,EMAIL,JOINED_DATE FROM person WHERE ID=?";
	public static final String SELECT_ALL_QUERY="SELECT ID,FIRST_NAME,LAST_NAME,EMAIL,JOINED_DATE FROM person";
	public static final String DELETE_QUERY="DELETE FROM person WHERE ID=?";
	public static final String DELETE_ALL_QUERY="DELETE FROM person";
	
	
	public void insertPerson(Person p) throws SQLException{
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
			ps.setLong(1, p.getId());
			ps.setString(2, p.getFirstName());
			ps.setString(3, p.getLastName());
			ps.setString(4, p.getEmail());
			ps.setDate(5, new java.sql.Date(p.getJoinedDate().getTime()));
			
			ps.execute();
			System.out.println("Insert person => "+ps.toString());
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
	
	public ArrayList<Person> retrivePersons() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			con =JDBCConnection.getConnection();
			if (con == null) {
				System.out.println("Connection Error");
				return persons;
			}
			ps = con.prepareStatement(SELECT_ALL_QUERY);
			rs = ps.executeQuery();
			System.out.println("retrivePersons => "+ps.toString());
			while (rs.next()) {
				Person p =new Person();
				p.setId(rs.getLong("ID"));
				p.setFirstName(rs.getString("FIRST_NAME"));
				p.setLastName(rs.getString("LAST_NAME"));
				p.setEmail(rs.getString("EMAIL"));
				p.setJoinedDate(rs.getDate("JOINED_DATE"));
				persons.add(p);
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
		return persons;
	}
	
	public Person retrivePerson(long id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Person person = new Person();
		try {
			con = JDBCConnection.getConnection();
			if (con == null) {
				System.out.println("Connection Error");
				return person;
			}
			ps = con.prepareStatement(SELECT_QUERY);
			ps.setLong(1, id);
			rs= ps.executeQuery();
			System.out.println("retrivePerson => "+ps.toString());
			while (rs.next()) {
				Person p =new Person();
				p.setId(rs.getLong("ID"));
				p.setFirstName(rs.getString("FIRST_NAME"));
				p.setLastName(rs.getString("LAST_NAME"));
				p.setEmail(rs.getString("EMAIL"));
				p.setJoinedDate(rs.getDate("JOINED_DATE"));
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
		return person;
	}
	

	public void updatePersonFirstName(Person person)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCConnection.getConnection();
			if (con == null) {
				System.out.println("Connection Error");
				return;
			}
			con.setAutoCommit(false);
			ps=con.prepareStatement(UPDATE_QUERY);
			ps.setString(1, person.getFirstName());
			ps.setLong(2, person.getId());
			ps.execute();
			System.out.println("Update person first name => "+ps.toString());
			
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
	
	public void deleteAllRecords()throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCConnection.getConnection();
			if (con == null) {
				System.out.println("Connection Error");
				return;
			}
			ps = con.prepareStatement(DELETE_ALL_QUERY);
			ps.execute();
			System.out.println("Delete All records => "+ps.toString());
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
	
	public void deletePerson(int id) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCConnection.getConnection();
			ps = con.prepareStatement(DELETE_QUERY);
			ps.setLong(1, id);
			ps.execute();
			System.out.println("Delete Person => "+ps.toString());
			
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
	
}
