package build;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBUtil implements SqlDao{
	final private String url="jdbc:mysql://localhost:3306/jspdemo?serverTimezone=UTC&useSSL=false";
	final private String username="root";
	final private String pwd="123456";
	final private String driver = "com.mysql.cj.jdbc.Driver";
	Connection conn;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DBUtil();
	}

	public DBUtil() {
		try {
			Class.forName(driver);		//加载类
			conn = DriverManager.getConnection(url,username,pwd);	//链接数据库
			PreparedStatement stmt = conn.prepareStatement("select * from logintable");	//预处理sql语句
			//查询
//			String Sql="select * from logintable";
//			Map<String, String> values=new HashMap<>();
//			values.put("username","356");
//			values.put("password","123");
//			ResultSet rs= this.selcetexecute(Sql,values);
//			//ResultSet rs= this.selcetexecute(Sql,null); //全表查询
//			while(rs.next()) {
//				System.out.println(rs.getString("username"));
//				System.out.println(rs.getString("password"));
//			}
			//插入
			String Sql="insert into logintable values (?, ?), (?, ?)";
			int resultint = this.insertexecute(Sql, new String[] {"361","124","377","111"});
			System.out.println(resultint);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ResultSet selcetexecute(String Sql, Map<String, String> values) {
		// TODO Auto-generated method stub
		PreparedStatement stmt;
		try {
			StringBuffer stb = new StringBuffer(Sql);
			if(values!=null) {
				stb.append(" where ");
				boolean flag = true;
				for(Map.Entry<String,String> entry:values.entrySet()) {
					if(flag) {
						flag=false;
						stb.append(entry.getKey()+"='"+entry.getValue()+"' ");
					}
					stb.append("and "+entry.getKey()+"='"+entry.getValue()+"' ");
				}
			}
			
			stmt = conn.prepareStatement(stb.toString());
			return stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int insertexecute(String Sql, String[] arg) {
		// TODO Auto-generated method stub
		PreparedStatement stmt;
		try {
			
			stmt = conn.prepareStatement(Sql);
			for(int i=0;i<arg.length;i++)
				stmt.setString(i+1,arg[i]);
			//修改和插入使用executeUpdate（）
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
