package service;

import java.sql.SQLException;
import java.util.Date;

import dao.PersonDao;
import domain.Person;

public class PersonService {
	
	public static void main(String[] args) {
		
		PersonDao pDao = new PersonDao();
		Person p = new Person(1,"Sampath","Kumara","sampath@gmail.com", new Date());
		
		try {
			pDao.insertPerson(p);
			System.out.println("Insert Person ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
