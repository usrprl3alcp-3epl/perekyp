package org.maxi.booter.web;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("testing")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractRestTest<T> {

	final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
	
	@Autowired
	CrudRepository<T, Long> repository;
	@Autowired
	WebApplicationContext wac;
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	// TODO Need to learn Null and Exception handling best practices
	public static Long parseIdFromLocationHeader(MvcResult result) {
		if (result == null) {
			// Maybe throw custom exception will be better here
			return null;
		}
		String locationHeader = result.getResponse().getHeader("Location");
		if (locationHeader == null) {
			// Maybe throw custom exception will be better here
			return null;
		}
		
		return parseIdFromLink(locationHeader);
	}
	
	// TODO
	public static Long parseIdFromLink(String link) {
		Long id = null;
		// Maybe pattern verification and throwing custom exception will be better here
		try {
			id = Long.parseLong(link.substring(link.lastIndexOf("/") + 1));
		} catch (Exception ignore) {}

		return id;
	}
}
