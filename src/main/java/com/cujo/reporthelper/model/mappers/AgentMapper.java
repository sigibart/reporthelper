package com.cujo.reporthelper.model.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;

import com.cujo.reporthelper.model.Agent;

@Mapper
public interface AgentMapper {
	
	String FIND_WITHOUT_ADDRESS_SQL = 
			"SELECT DISTINCT "
			+ "a.id "
			+ ", a.status "
			+ ", a.latitude "
			+ ", a.longitude "
			+ " FROM agent a "
			+ " LEFT JOIN agent_address aa on aa.agent_id = a.id "
			+ " WHERE aa.agent_id IS NULL"
			+ " AND a.latitude IS NOT NULL";
	
	@Select(FIND_WITHOUT_ADDRESS_SQL)
	List<Agent> findWithoutAddress() throws PersistenceException;
	

}
