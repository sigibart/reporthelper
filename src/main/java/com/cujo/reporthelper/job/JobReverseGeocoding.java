package com.cujo.reporthelper.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cujo.reporthelper.model.Agent;
import com.cujo.reporthelper.model.AgentAddress;
import com.cujo.reporthelper.model.mappers.AgentAddressMapper;
import com.cujo.reporthelper.model.mappers.AgentMapper;
import com.cujo.reporthelper.services.ReverseGeocodingService;

@Component
public class JobReverseGeocoding {

private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="${job.impl}")
	ReverseGeocodingService service;
	
	List<Agent> agentList;
	
	@Resource
	AgentMapper agentMapper;
	@Resource
	AgentAddressMapper agentAddressMapper;
	
	//@Scheduled(fixedDelayString = "${job.fixedDelay}")
	@Scheduled(cron = "${job.cron}")
	public void run() throws InterruptedException {
		
		try {
			agentList = agentMapper.findWithoutAddress();
			log.info(String.format("Started with: %s records", agentList.size()));
		} catch (Exception e){
			log.error("JobReverseGeocoding run - failed to query findWithoutAddress ", e);
			return;
		}
		
		if (agentList == null || agentList.isEmpty())
			return;
						
		for (Agent agent : agentList) {			
			
			try {				
				AgentAddress agentAddress = agentAddressMapper.findByCoordinates(agent.getLatitude(), agent.getLongitude());				
				if (agentAddress == null)
					agentAddress = service.convert(agent);
				agentAddress.setAgentId(agent.getId());
				agentAddress.setLatitude(agent.getLatitude());
				agentAddress.setLongitude(agent.getLongitude());				
				if (agentAddress != null && agentAddress.getCountry() != null)
					agentAddress.setStatus(AgentAddress.STATUS_SUCCESS);
				else
					agentAddress.setStatus(AgentAddress.STATUS_FAILED);				
				agentAddressMapper.insert(agentAddress);				
			} catch (Exception e) {
				log.error("JobReverseGeocoding run - failed to convert ", e);
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e){
				log.error("JobReverseGeocoding run - failed sleep ", e);
			}
		}
		
	}
	
}
