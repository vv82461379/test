package mytest;

import com.shop.utils.BaseUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 测试的junit单元要放在test中，test要更改为test文件
 */
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
