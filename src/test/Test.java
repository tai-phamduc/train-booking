package test;

import dao.AccountDAO;
import entity.Account;
import entity.Employee;

public class Test {

	public static void main(String[] args) {
		
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.createAccount(new Account("admin", "123", new Employee("1")));
		
	}
	
}
