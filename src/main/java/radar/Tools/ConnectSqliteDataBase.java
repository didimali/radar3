package radar.Tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
		this.url += url;
	}
	
	public HashMap<String,List> selectAllData(){		
		HashMap<String,List> map = new HashMap<String,List>();
		Connection connection = null;
		try {
            connection = createConnection();
            List<Records> records = getRecords(connection);
            if(records != null && records.size() >0)
            	map.put("Records",records);
            List<Faults> faults = getFaults(connection);
            if(faults != null && faults.size() >0)
            	map.put("Faults",faults);
            return map;
        }  catch (SQLException | ClassNotFoundException | UnsupportedEncodingException | ParseException e) {
            System.err.println(e.getMessage());
            return null;
        }finally{
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
        ResultSet rs = statement.executeQuery("select * from Faults");
        while (rs.next()) {
        	Faults r = new Faults();
        	SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmss"); 
            int col1 = rs.getInt("id");
            String col2 = rs.getString("time");
            byte[] byteInfo = rs.getBytes("info");
            String info = null;
            if(!(byteInfo == null || byteInfo.length ==0 ))
            	info = new String(byteInfo,"GBK");
            int col4 = rs.getInt("dev");
            r.setId(col1);
            r.setTime(sdf.parse(col2));
            r.setDev(col4);
            r.setInfo(info);
            if(info != null)
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
        	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMddHHmmss" ); 
            int col1 = rs.getInt("id");
            String col2 = rs.getString("time");
            byte[] byteInfo = rs.getBytes("info");
            String info = null;
            if(!(byteInfo == null || byteInfo.length ==0 ))
            	info = new String(byteInfo,"GBK");
            int col4 = rs.getInt("dev");
            r.setId(col1);
            r.setTime(sdf.parse(col2));
            r.setDev(col4);
            r.setInfo(info);
            if(info != null)
            	result.add(r);           
        }
        return result;
    }
   
    
}
