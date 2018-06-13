import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;

import com.almundo.customerprocessor.SpringBootConsoleApplication;

import t.CustomerDataSet;


public class BaseTest {

	public BaseTest() {
	}

	@Before
	public void setup() {
		writeInput(createInput(getDefaultDataSet()));
		File file = new File("db");
		file.mkdir();
	}

	@After
	public void tearDown() throws Exception {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:db/testdb", "SA", "");
			Statement statement = conn.createStatement();
	
			statement.executeUpdate("DROP table customer;");
			statement.executeUpdate("DROP table address;");
			conn.close();
	
			writeInput(createInput(getDefaultDataSet()));
			File file = new File("db");
			file.mkdir();
		} catch (Exception e) {
		}
	}

	public CustomerDataSet getDefaultDataSet() {
		return new CustomerDataSet("Chavo", "Chimoltrufia", "Chompiras", "Corrientes", "Rivadavia", "Cordoba", "Santa fe", "100", "201", "2");
	}

	public String createResult(CustomerDataSet customerDataSet) {
		String customer1 = deleteQuotes(customerDataSet.getCustomer1());
		String addressNumber1 = deleteQuotes(customerDataSet.getAddressNumber1());
		return "10 - address of " + customer1 + " - " + customerDataSet.getAddress1() + " - " + addressNumber1 + " - 1\n" + //
				"11 - address of " + customer1 + " - " + customerDataSet.getAddress1() + " - 101 - 1\n" + //
				"12 - address of " + customer1 + " - " + customerDataSet.getAddress2() + " - 103 - 1\n" + //
				"20 - address of " + customerDataSet.getCustomer2() + " - " + customerDataSet.getAddress3() + " - 202 - 2\n" + //
				"21 - address of " + customer1 + " - " + customerDataSet.getAddress3() + " - " + customerDataSet.getAddress3Number2() + " - 1\n" + //
				"30 - address of " + customerDataSet.getCustomer3() + " - " + customerDataSet.getAddress4() + " - 303 - 3\n" + //
				"31 - address of " + customerDataSet.getCustomer2() + " - " + customerDataSet.getAddress4() + " - 302 - 2\n";
	}

	private String deleteQuotes(String customer12) {
		return customer12.replace("\"", "");
	}

	protected String createInput(CustomerDataSet customerDataSet) {
	
		String input = "C,1," + customerDataSet.getCustomer1() + ",1000\n" + //
				"A,10," + customerDataSet.getAddress1() + "," + customerDataSet.getAddressNumber1() + ",1\n" + //
				"A,11," + customerDataSet.getAddress1() + ",101,1\n" + //
				"A,12," + customerDataSet.getAddress2() + ",103,1\n" + //
				"C,2," + customerDataSet.getCustomer2() + ",2000\n" + //
				"A,20," + customerDataSet.getAddress3() + ",202," + customerDataSet.getCustomer2Id() + "\n" + //
				"A,21," + customerDataSet.getAddress3() + "," + customerDataSet.getAddress3Number2() + ",1\n" + //
				"C,3," + customerDataSet.getCustomer3() + ",3000\n" + //
				"A,30," + customerDataSet.getAddress4() + ",303,3\n" + //
				"A,31," + customerDataSet.getAddress4() + ",302,2\n";
		return input;
	}

	protected String executeProcessor(String input, String result) {
		try {
			writeInput(input);
	
			ByteArrayOutputStream temporalOut = new ByteArrayOutputStream();
			PrintStream lastOut = System.out;
			System.setOut(new PrintStream(temporalOut));
	
			SpringBootConsoleApplication.main(new String[] {});
			// new CustomerProcessor().main(null);
	
			System.setOut(lastOut);
	
			String string = new String(temporalOut.toByteArray());
			int max = Math.max(string.indexOf("address"), 0) - 5;
			return string.substring(max, max + result.length());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void writeInput(String input) {
		try (
				FileWriter fileWriter = new FileWriter(new File("input.txt"))) {
			fileWriter.write(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}