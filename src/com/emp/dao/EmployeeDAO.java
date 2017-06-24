package com.emp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emp.entity.Employee;

public class EmployeeDAO {

	public  int deleteEmployee(int employeeId) throws SQLException
	{
		String query = "delete from employee where id = "+employeeId;
		System.out.println(query);
		Statement statement = getStatement();
		int status = statement.executeUpdate(query);
		return status;
	}
	public  int updateEmployee(Employee employee) throws SQLException
	{
		String query = "update employee set name='"+employee.getName()+"',doj ='"+employee.getDoj()+"',deg ='"+employee.getDeg()+"' where id ='"+employee.getId()+"'";
		System.out.println(query);
		Statement statement = getStatement();
		int status = statement.executeUpdate(query);
	   return status;
	}
	
	public  Employee getEmployee(int employeeId) throws SQLException
	{
		Employee employee= new Employee();
		String query = "select * from employee where id = "+employeeId;
		System.out.println(query);
		Statement statement = getStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next())
		{
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String doj = resultSet.getString("doj");
			String deg = resultSet.getString("deg");
			
			employee.setDeg(deg);
			employee.setDoj(doj);
			employee.setId(id);
			employee.setName(name);;
		}
		return employee;
	}
	
	public  int insertData(Employee employee) throws SQLException
	{
		String query = "insert into employee values('"+employee.getId()+"','"+employee.getName()+"','"+employee.getDoj()+"','"+employee.getDeg()+"')";
		System.out.println(query);
		Statement statement = getStatement();
		int status = statement.executeUpdate(query);
	   return status;
	}
	public  List<Employee> getEmployees() throws SQLException
	{
		List<Employee> employees = new ArrayList<>();
		Statement statement = getStatement();
		ResultSet resultSet = statement.executeQuery("select * from employee");
		while(resultSet.next())
		{
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String doj = resultSet.getString("doj");
			String deg = resultSet.getString("deg");
			Employee employee= new Employee();
			employee.setDeg(deg);
			employee.setDoj(doj);
			employee.setId(id);
			employee.setName(name);;
			employees.add(employee);
		}
		return employees;
		
	}
	/**
	 * This method give statement object which is given by connection object.
	 * @return statement of type Statement
	 */
	private  Statement getStatement()
	{
		Statement statement = null;
		Connection connection = null;
	
		try
		{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			connection = DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","system","ashok");  
			 statement = connection.createStatement(); 
		 }
		catch (SQLException e) 
		{
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			connection = null;
		}
		return statement;
			
	}
}
