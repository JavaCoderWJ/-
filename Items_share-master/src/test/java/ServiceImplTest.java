


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.HomePageService;
import service.PersonalCenterService;
import util.RequestStatusException;
import util.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class ServiceImplTest {

	@Resource
	private HomePageService homePageService;
	private PersonalCenterService personalCenterService;
	private HttpServletRequest request;


	@Test
	public void test() throws RequestStatusException {
		// System.out.println(serviceImpl.selectFilm());
		 Status status = homePageService.getItemsById(48);
		//Status status = personalCenterService.getPersonalDetails(request);
		System.out.println(status.toString());
	}
}
