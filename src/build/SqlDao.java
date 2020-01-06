package build;

import java.sql.ResultSet;

import java.util.Map;

public interface SqlDao {
	/**
	 * select
	 * @param Sql
	 * @param values
	 * @return
	 */
	public ResultSet selcetexecute(String Sql,Map<String,String> values);	//²é
	/**
	 * insert
	 * @param Sql
	 * @param values
	 * @return
	 */
	public int insertexecute(String Sql,String[] arg);  //Ôö
}
