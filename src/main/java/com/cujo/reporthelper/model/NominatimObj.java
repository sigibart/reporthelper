package com.cujo.reporthelper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NominatimObj {
	
	public AgentAddress address;

}
