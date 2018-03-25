package souce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public class Main {
	public static int user;
	public static String pass;

	public static void main(String[] args) throws Exception {
		menu();
		if (user == 1) {
			supporter();
		} else if (user == 2) {
			ref();
		} else if (user == 3) {
			coach();
		} else if (user == 4) {
			president();
		}
	}

	public static void menu() {
		System.out.println("Διαλεξτε το ειδος χρηστη:");
		System.out.println("1:Φιλαθλος");
		System.out.println("2:Διαιτητης");
		System.out.println("3:Προπονητης");
		System.out.println("4:Προεδρος");
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		user = sc.nextInt();
		if (user < 1 || user > 4) {
			System.out.println("The program will terminate now");
			System.exit(1);
		} else if (user == 1) {
			int flag = 0;
			System.out.println("Καλως ηρθατε Φιλαθλε");
			do {
				System.out.println("Δωστε συνθηματικο:");
				pass = sc2.nextLine();
				if (pass.equals("sup")) {
					flag = 1;
				}
				if (flag == 0)
					System.out.println("Λαθος κωδικος");
			} while (flag == 0);
		} else if (user == 2) {
			int flag = 0;
			System.out.println("Καλως ηρθατε διαιτητη");
			do {
				System.out.println("Δωστε συνθηματικο:");
				pass = sc2.nextLine();
				if (pass.equals("ref")) {
					flag = 1;
				}
				if (flag == 0)
					System.out.println("Λαθος κωδικος");
			} while (flag == 0);
		} else if (user == 3) {
			int flag = 0;
			System.out.println("Καλως ηρθατε προπονητη");
			do {
				System.out.println("Δωστε συνθηματικο:");
				pass = sc2.nextLine();
				if (pass.equals("co")) {
					flag = 1;
				}
				if (flag == 0)
					System.out.println("Λαθος κωδικος");
			} while (flag == 0);
		} else if (user == 4) {
			int flag = 0;
			System.out.println("Καλως ηρθατε προεδρε");
			do {
				System.out.println("Δωστε συνθηματικο:");
				pass = sc2.nextLine();
				if (pass.equals("pre")) {
					flag = 1;
				}
				if (flag == 0)
					System.out.println("Λαθος κωδικος");
			} while (flag == 0);
		}
	}

	public static void supporter() throws Exception {
		int ch;
		String name = "\0";
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/league?useSSL=false";
		final String USER = "root";
		final String PASS = "";
		Connection conn = null;
		Statement stmt = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		int sid;
		System.out.println("Δωστε μου το supporter id σας");
		Scanner sc3 = new Scanner(System.in);
		sid = sc3.nextInt();
		Scanner sc4 = new Scanner(System.in);
		do {
			System.out.println("1:Αναζητηση αγωνα με βαση το id του");
			System.out.println("2:Διαφορα τερματων");
			System.out.println("3:Ποιους αγωνες παρακολουθησατε");
			System.out.println("4:Εχει γινει προταση για ανανεωση;");
			System.out.println("5:Γκολ αγαπημενου παιχτη");
			ch = sc4.nextInt();
			if (ch == 1) {
				int m_id;
				System.out.println("Δωστε το ID του αγωνα");
				Scanner sc5 = new Scanner(System.in);
				m_id = sc5.nextInt();
				sql = "CALL matchid(" + m_id + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ωρα και ημερομηνια "+rs.getString("dts"));
					if (rs.getString("ds").equals("0")) {
						System.out.println("Τοποθεσια "+rs.getString("loc"));
						System.out.println("Διαιτητες "+rs.getString("name1"));
						System.out.println(rs.getString("name2"));
						System.out.println(rs.getString("name3"));
						System.out.println(rs.getString("name4"));
						System.out.println(rs.getString("name5"));
					} else {
						System.out.println("Περιγραφη "+rs.getString("ds"));
						System.out.println("Αποτελεσμα "+rs.getString("score"));
					}
				}
			}
			if (ch == 2) {
				sql = "CALL gol(" + sid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ονομα "+rs.getString("name"));
					System.out.println("Διαφορα "+rs.getInt("given-taken"));
				}
			} else if (ch == 5) {
				sql = "CALL fgol(" + sid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ονομα "+rs.getString("name"));
					System.out.println("Γκολ "+rs.getInt("goals"));
				}
			} else if (ch == 4) {
				sql = "CALL ren(" + sid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ονομα "+rs.getString("name"));
					if (rs.getInt("renew") == 1) {
						System.out.println("Σας εχει γινει προταση για ανανεωση/αποκτηση εισιτηριου διαρκειας");
					} else {
						System.out.println("Δεν σας εχει γινει προταση για ανανεωση/αποκτηση εισιτηριου διαρκειας");
					}
				}
			} else if (ch == 3) {
				sql = "CALL ats(" + sid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Κωδ "+rs.getString("id"));
					System.out.println("Ωρα και ημερομηνια "+rs.getString("dt"));
					System.out.println("Αποτελεσμα "+rs.getString("score"));
					System.out.println("Σκορ "+rs.getString("result"));
					System.out.println("Περιγραφη "+rs.getString("des"));
					System.out.println("Ομαδα1 "+rs.getString("team1"));
					System.out.println("Ομαδα2 "+rs.getString("team2"));
					System.out.println("Τοποθεσια "+rs.getString("location"));
				}
			}
		} while (ch < 6 && ch > 0);
	}

	public static void ref() throws Exception {
		String name = "\0";
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/league?useSSL=false";
		final String USER = "root";
		final String PASS = "";
		Connection conn = null;
		Statement stmt = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		int rid;
		System.out.println("Δωστε μου το ref id σας");
		Scanner sc3 = new Scanner(System.in);
		rid = sc3.nextInt();
		sql = "CALL rf(" + rid + ");";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println("Ωρα και ημερομηνια "+rs.getString("dt"));
			System.out.println("Τοποθεσια "+rs.getString("location"));
			System.out.println("Ομαδα1 "+rs.getString("team1"));
			System.out.println("Ομαδα2 "+rs.getString("team2"));
			if (rs.getInt("r1") == rid) {
				System.out.println("Ρολος "+rs.getString("ro1"));
			} else if (rs.getInt("r2") == rid) {
				System.out.println("Ρολος "+rs.getString("ro2"));
			} else if (rs.getInt("r3") == rid) {
				System.out.println("Ρολος "+rs.getString("ro3"));
			} else if (rs.getInt("r4") == rid) {
				System.out.println("Ρολος "+rs.getString("ro4"));
			} else {
				System.out.println("Ρολος "+rs.getString("ro5"));
			}
		}
	}

	public static void coach() throws Exception {
		String name = "\0";
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/league?useSSL=false";
		final String USER = "root";
		final String PASS = "";
		Connection conn = null;
		Statement stmt = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		int cid;
		System.out.println("Δωστε το coach id σας");
		Scanner sc3 = new Scanner(System.in);
		cid = sc3.nextInt();
		int ch;
		do {
			System.out.println("1:Επομενοι αγωνες");
			System.out.println("2:Στατιστηκα");
			System.out.println("3:Παικτες");
			System.out.println("4:Προσθηκη παικτη");
			Scanner sc4 = new Scanner(System.in);
			ch = sc4.nextInt();
			if (ch == 1) {
				sql = "CALL onmc(" + cid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ημερα και ωρα "+rs.getString("dt"));
					System.out.println("Τοποθεσια "+rs.getString("location"));
				}
			} else if (ch == 2) {
				sql = "CALL st(" + cid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ονομα "+rs.getString("name"));
					System.out.println("Ποντοι "+rs.getString("points"));
					System.out.println("Εβαλαν "+rs.getString("given"));
					System.out.println("Δεχτηκαν "+rs.getString("taken"));
					System.out.println("Νικες "+rs.getString("won"));
					System.out.println("Ηττες "+rs.getString("lost"));
					System.out.println("Ισσοπαλιες "+rs.getString("tie"));
					
				}
			} else if ( ch == 3 ) {
				sql = "CALL plr(" + cid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Ονομα "+rs.getString("name"));
				}
			} else if ( ch == 4 ){
				System.out.println("Καθε παιχτης πρεπει αν ειναι της μορφης ονομα , ηλικια , γκολ , περιγραφη , θεση");
				String name1,des,pos;
				int age,goal;
				System.out.println("Δωσε το ονομα του παικτη");
				Scanner sc5 = new Scanner(System.in);
				name1=sc5.nextLine();
				System.out.println("Δωσε ηλικια");
				Scanner sc6 = new Scanner(System.in);
				age=sc6.nextInt();
				System.out.println("Δωσε γκολ");
				Scanner sc7 = new Scanner(System.in);
				goal=sc7.nextInt();
				System.out.println("Δωσε περιγραφη");
				Scanner sc8 = new Scanner(System.in);
				des=sc8.nextLine();
				System.out.println("Δωσε θεση");
				Scanner sc9 = new Scanner(System.in);
				pos=sc9.nextLine();
				sql = "CALL adpl("+cid+",\""+name1+"\",\""+age+"\",\""+goal+"\",\""+des+"\",\""+pos+"\");";
				stmt.execute(sql);
			}
		} while (ch > 0 && ch < 5);
	}

	public static void president() throws Exception {
		String name = "\0";
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/league?useSSL=false";
		final String USER = "root";
		final String PASS = "";
		Connection conn = null;
		Statement stmt = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		int pid;
		int ch;
		System.out.println("Δωστε το president id σας");
		Scanner sc3 = new Scanner(System.in);
		pid = sc3.nextInt();
		do {
			System.out.println("1:Εσοδα");
			System.out.println("2:Εισιτηρια");
			System.out.println("3:Προσφορα");
			Scanner sc4 = new Scanner(System.in);
			ch = sc4.nextInt();
			if (ch == 1) {
				sql = "CALL mon(" + pid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Εσοδα "+rs.getString("m"));
				}
			} else if (ch == 2) {
				sql = "CALL tik(" + pid + ");";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					System.out.println("Διακειας "+rs.getString("Diarkias"));
					System.out.println("Απλα "+rs.getString("Apla"));
				}
			} else if ( ch == 3 ) {
				System.out.println("Δωστε id οπαδου για ανανεωση");
				int sid;
				Scanner sc5 = new Scanner(System.in);
				sid = sc5.nextInt();
				sql = "CALL up(" + pid + ","+sid+");";
				stmt.execute(sql);
			}
		} while (ch > 0 && ch < 4);
	}
	
}
