package com.cujo.reporthelper.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cujo.reporthelper.model.Agent;
import com.cujo.reporthelper.model.AgentAddress;
import com.cujo.reporthelper.model.NominatimObj;

@Service("nominatim")
public class ReverseGeocodingNominatimImp implements ReverseGeocodingService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	private String reverseUrl;
	private final String email = "sigitas.bartusevicius@cujo.com";
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public ReverseGeocodingNominatimImp (RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public AgentAddress convert(Agent agent) throws Exception {
		AgentAddress agentAddress = null;
		
		String emailEncoded;
        try {
            emailEncoded = URLEncoder.encode(email, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            emailEncoded = email;
        }
		
		this.reverseUrl = String.format("https://nominatim.openstreetmap.org/reverse?format=json&email=%s&lat=%s&lon=%s&zoom=18&addressdetails=1", emailEncoded, agent.getLatitude(), agent.getLongitude());
		
		NominatimObj obj = restTemplate.getForObject(reverseUrl, NominatimObj.class);
		agentAddress = obj.address;
		
		
		return agentAddress;
		
	}

}
