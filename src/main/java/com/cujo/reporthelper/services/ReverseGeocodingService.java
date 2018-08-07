package com.cujo.reporthelper.services;

import com.cujo.reporthelper.model.Agent;
import com.cujo.reporthelper.model.AgentAddress;

public interface ReverseGeocodingService {
		
	public AgentAddress convert(Agent agent) throws Exception;

}
