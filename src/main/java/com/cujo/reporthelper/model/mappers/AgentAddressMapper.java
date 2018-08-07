package com.cujo.reporthelper.model.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;

import com.cujo.reporthelper.model.Agent;
import com.cujo.reporthelper.model.AgentAddress;

@Mapper
public interface AgentAddressMapper {
	
	String INSERT_SQL = "insert into agent_address (agent_id, status, country, code, state, city, county, town, village, hamlet)"
			+ " values ("
            + " #{agentId, jdbcType=NUMERIC},"
            + " #{status, jdbcType=NUMERIC},"
            + " #{country, jdbcType=VARCHAR},"
            + " #{code, jdbcType=VARCHAR},"
            + " #{state, jdbcType=VARCHAR},"
            + " #{city, jdbcType=VARCHAR},"
            + " #{county, jdbcType=VARCHAR},"
            + " #{town, jdbcType=VARCHAR},"
            + " #{village, jdbcType=VARCHAR},"
            + " #{hamlet, jdbcType=VARCHAR}"            
            + " )";
	@Insert(INSERT_SQL)
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(AgentAddress data) throws PersistenceException;

}
