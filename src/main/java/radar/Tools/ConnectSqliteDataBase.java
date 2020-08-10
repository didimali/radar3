package radar.Tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

/**
 * 动态连接sqlite数据库
 * @author madi
 *
 */
public class ConnectSqliteDataBase {
	
	private static String url = "jdbc:sqlite:";
	private final static String Class_Name = "org.sqlite.JDBC";
	
	/**
	 * 
	 * @param url sqlite数据库地址
	 */
	public ConnectSqliteDataBase(String url) {
		this.url = this.url + url;
	}
	
	/**
	 * 查询数据库Records表数据
	 * @return
	 */
	public List<Records> selectAllRecords() {
		List<Records> result = new ArrayList<Records>();	 
        Connection connection = null;
        try {
            connection = createConnection();
            result = getRecords(connection);
            url = "jdbc:sqlite:";
            return result;
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
	
	/**
	 * 查询数据库Faults表数据
	 * @return
	 */
	public List<Faults> selectAllFaults() {
		List<Faults> result = new ArrayList<Faults>();	 
        Connection connection = null;
        try {
            connection = createConnection();
            result = getFaults(connection);
            url = "jdbc:sqlite:";
            return result;
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
	    
    private List<Faults> getFaults(Connection connection) throws SQLException, UnsupportedEncodingException, ParseException {
    	List<Faults> result = new ArrayList<Faults>();
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); 
        // 执行查询语句
        ResultSet rs = statement.executeQuery("SELECT * FROM Faults");
        while (rs.next()) {
        	Faults r = new Faults();
        	SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
            int col1 = rs.getInt("id");
            String col2 = rs.getString("time");
            String col3 = new String(rs.getString("timeb").getBytes("UTF-8"),"GBK");
            int col4 = rs.getInt("dev");
            String col5 = rs.getString("info");
            r.setId(col1);
            r.setTime(sdf.parse(col2));
            r.setTimeb(col3);
            r.setDev(col4);
            r.setInfo(col5);
            result.add(r);           
        }
        return result;
	}

	// 创建Sqlite数据库连接
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
        return DriverManager.getConnection(url);
    }

    public List<Records> getRecords(Connection connection) throws SQLException, UnsupportedEncodingException, ParseException {
    	List<Records> result = new ArrayList<Records>();
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); 
        // 执行查询语句
        ResultSet rs = statement.executeQuery("SELECT * FROM Records");
        while (rs.next()) {
        	Records r = new Records();
        	SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
            int col1 = rs.getInt("id");
            String col2 = rs.getString("time");
            String col3 = new String(rs.getString("timeb").getBytes("UTF-8"),"GBK");
            int col4 = rs.getInt("dev");
            String col5 = rs.getString("info");
            r.setId(col1);
            r.setTime(sdf.parse(col2));
            r.setTimeb(col3);
            r.setDev(col4);
            r.setInfo(col5);
            result.add(r);           
        }
        return result;
    }
}
