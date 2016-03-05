import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class QOTDDBHandler {
	private Connection db;
	Random randomNumberGenerator = new Random();
	QOTDDBHandler() {
		try {
			Class.forName("org.sqlite.JDBC");
			db = DriverManager.getConnection("jdbc:sqlite:quotes.db");
		} catch (SQLException  e) {
			e.printStackTrace();
			System.out.println("Unable to create/access quotes DB. Shutting down.");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getQuote() {
		try {
			Statement countQuery = db.createStatement();
			ResultSet rs = countQuery.executeQuery("SELECT count(*) AS count FROM quotes");
			rs.next();
			int count = rs.getInt("count");
			
			if(count == 0) {
				System.out.println("No quotes are available on the server!");
				System.exit(1);
			}
			
			int rand = randomNumberGenerator.nextInt(count) + 1;
			System.out.println(rand);
			Statement getQuoteQuery = db.createStatement();
			rs = getQuoteQuery.executeQuery("SELECT quote FROM quotes WHERE id = " + rand);
			rs.next();
			return rs.getString("quote");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
