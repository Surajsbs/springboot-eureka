package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

@RestController
@RequestMapping("/info")
public class EurekaClientController {
	@Autowired
	private EurekaClient eurekaClient;

	@RequestMapping("/greeting")
	public String greeting() {
		return "Client is up and running!";
	}

	@RequestMapping("/get-registers-services")
	public Applications getRegisteredServices() {
		return eurekaClient.getApplications();
	}

	@RequestMapping("/get-instance/{instanceName}/{isSecured}")
	public List<InstanceInfo> getListOfAvailableInstanceInfo(@PathVariable("instanceName") String instanceName,
			@PathVariable("isSecured") boolean isSecured) {

		return eurekaClient.getInstancesByVipAddress(instanceName, isSecured);
	}
}