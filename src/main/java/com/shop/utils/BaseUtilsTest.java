package com.shop.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseUtilsTest {
	
	private String str;

	@Before
	public void setUp() throws Exception {
		str = "15711318856";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShelterMobile() {
		String reString = BaseUtils.shelterMobile(str);
		assertEquals("157****8856", reString);
	}

}
