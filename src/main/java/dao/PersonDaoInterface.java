package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Person;

public interface PersonDaoInterface {

	void insertPerson(Person p) throws SQLException;

	ArrayList<Person> retrivePersons() throws SQLException;

	Person retrivePerson(long id) throws SQLException;

	void updatePersonFirstName(Person person) throws SQLException;

	void deleteAllRecords() throws SQLException;

	void deletePerson(int id) throws SQLException;

}
