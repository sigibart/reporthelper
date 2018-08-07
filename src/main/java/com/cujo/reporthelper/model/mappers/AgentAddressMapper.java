package com.cujo.reporthelper.model.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;

import com.cujo.reporthelper.model.AgentAddress;

@Mapper
public interface AgentAddressMapper {
	
	String INSERT_SQL = "insert into agent_address (agent_id, status, latitude, longitude, country, code, state, city, county, town, village, hamlet)"
			+ " values ("
            + " #{agentId, jdbcType=NUMERIC},"
            + " #{status, jdbcType=NUMERIC},"
            + " #{latitude, jdbcType=NUMERIC},"
            + " #{longitude, jdbcType=NUMERIC},"
            + " #{country, jdbcType=VARCHAR},"
            + " #{code, jdbcType=VARCHAR},"
            + " #{state, jdbcType=VARCHAR},"
            + " #{city, jdbcType=VARCHAR},"
            + " #{county, jdbcType=VARCHAR},"
            + " #{town, jdbcType=VARCHAR},"
            + " #{village, jdbcType=VARCHAR},"
            + " #{hamlet, jdbcType=VARCHAR}"            
            + " )";
	
	String FIND_BY_COORD_SQL = 
			"SELECT "
			+ "country "
			+ ",code "
			+ ",state "
			+ ",city "
			+ ",town "
			+ ",village "
			+ ",hamlet "			
			+ " FROM agent_address "			
			+ " WHERE latitude = #{latitude}"
			+ " AND longitude =  #{longitude}"
			+ " limit 1";
	
	@Insert(INSERT_SQL)
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(AgentAddress data) throws PersistenceException;
	
	
	@Select(FIND_BY_COORD_SQL)
	AgentAddress findByCoordinates(@Param("latitude") Double latitude, @Param("longitude") Double longitude) throws PersistenceException;

}
