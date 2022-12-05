package org.vision.homebook;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// V - VO class type,P primary key type
public interface IDao<V,P> { 
	public int insert(V vo) throws SQLException;
	public int delete(P pk,String mem_id) throws SQLException;
	public int deleteById(String mem_id) throws SQLException;
	public int update(V vo) throws SQLException ;
	public V select(P pk) throws SQLException;
	public List<V> selectAll(String mem_id) throws SQLException;
	public List<V> selectByDay(Timestamp startDate,Timestamp endDate,String mem_id) throws SQLException;
}
