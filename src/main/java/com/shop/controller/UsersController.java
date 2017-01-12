package com.shop.controller;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shop.model.ItemDto;
import com.shop.model.Users;
import com.shop.service.UserService;
import com.shop.utils.JsonUtils;

/**
 * ��������� @ResponseBody��springmvc��ƴ����תҳ��ʧЧ����ΪĬ�Ϸ���html�ĸ�ʽ��
 * ʹ����@RequestBody��controller����ͨ�����ȷ�ʽ����ֻ����json��ʽ��get�����ܼ�@RequestBody.
 * @author garen_li
 *
 */

@Controller

public class UsersController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * RestTemplate��get����,ֻ�ܴ���String���͵�     ���� ����Ҫ�Ǳ���RequestId����  
	 */
	@RequestMapping("/testRestTemplateGet")
	public void testRestTemplateGet(){
		HttpHeaders headers = new HttpHeaders();

		String url = "http://localhost:8090/item/test?str=1";
		int integer = 1;
		String aaa = restTemplate.getForObject(url,String.class);
	}
	/**
	 * RestTemplate��post����,�������������Ƕ������ public @ResponseBody MyResponse processRequest(String RequestId, String count)
	 * ����Map���������ֵ������ֻ�ܴ���String���͵�     ���� ����Ҫ�Ǳ���RequestId����
	 * �ڵ��õĽӿ��ϱ����@ResponseBody�������޷��������
	 */
	@RequestMapping("/testRestTemplatePost")
	public void testRestTemplatePost(){
		HttpHeaders headers = new HttpHeaders();

		String url = "http://localhost:8090/item/listing";
		String str = "lixiang";
		String aaa = restTemplate.postForObject(url,str,String.class);
	}
	/**
	 * RestTemplate��post����ͨ����ʽ������json�������Խ���json,�Ѷ���תΪjsonҪ��org.codehaus.jackson�µĹ��ߣ���������٣������JSONObjectתΪ�ַ����������⡣
	 */
	@RequestMapping("/testRestTemplatePost1")
	public void testRestTemplatePost1(){

		String url = "http://localhost:8090/item/listing";
		ItemDto itemDto = new ItemDto();
		itemDto.setShopId(1);
		
		 HttpHeaders headers = new HttpHeaders();
	        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
	        headers.setContentType(type);
	        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	        
	        ObjectMapper mapper = new ObjectMapper(); //ת����  
	        String jsonObj = JsonUtils.object2Json(itemDto);
	        ItemDto itemDto2 = JsonUtils.json2Object(jsonObj,ItemDto.class);
	        
	        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

	        String result = restTemplate.postForObject(url, formEntity, String.class);
		
		
	}
	
	
	/**
	 * ����restful���@RequestMapping�е�{xv}Ҫ��@PathVariable("xv")��xv��ͬ�����ܻ�ȡ����String id�򲻱���ͬ�����Զ�������xv��ֵ��id��
	 * @param id
	 * @return
	 */
	  @RequestMapping("/test/{xv}")
	    public String queryByCondition(@PathVariable("xv") String id){
		  Long userId=Long.parseLong(id);
	        System.out.println(userId);
	        System.out.println("begin");
	        Users users = userService.queryById(userId);
	       //model����Ҫ����
//	        model.addAttribute("users", users);
	        return "selectStu";
	  }
	  
	  @RequestMapping("/save")
	  public void save(Users users){
		  userService.save(users);
	  }
	  
	  //ͨ��form��ȡһ��һ�����ݣ������Ի�ȡ
	  @RequestMapping("/print1")
	  public void prit1(@RequestParam("id") Long id,Model model){
		  System.out.println(id);
	  }
	  
	//��ȡform�е����ԣ�ֱ����postman������id  1  ,������Զ�ƥ�䵽users��
	  @RequestMapping("/print2")
	  public void prit2(Users user,Model model){
		  System.out.println(user.getId());
	  }
	  /**
	   * ��ȡjson��ʽ�Ķ���,@RequestBody���������ͷ��header��Content-Type�����ж�
	   * ��postman��header�з���Content-Type   application/json
	   * ��body��row������{"id":1}�ͻ��ڶ������Զ�ƥ��id=1ͨ��users.getId()���.
	   * ����jsonֻ����bean������У�����Users������ǲ���������userId�����ȡ����ǰ�˵�ֵ��
	   * @param users
	   * @param model
	   * @return
	   */
	   
	  @RequestMapping("/print")
	    public void queryByCondition(@RequestBody Users users,Model model){
			System.out.println(users.getId());
	  }
	  @RequestMapping("/paramtest")
	    public void paramtest(@RequestBody Integer userId){
			System.out.println(userId);
	  }
	  
	  /**
	     * �򵥵� md5 ����ģ��
	     */
	    private String encrypt(String password) {
	        MessageDigest md;
	        try {
	            md = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	        byte[] passwordInBytes = password.getBytes();
	        md.update(passwordInBytes);
	        return String.valueOf(new BigInteger(md.digest()).toString(16));

	    }
	    
	    /**
	     * ��תΪByte[]���飬������
	     */
	    private void transform(){
	    	 ByteArrayOutputStream out = new ByteArrayOutputStream();
//	    	 out.write(1);
	    	 byte[] b = out.toByteArray();
	    	 //base64���� ,encodeString��������
	    	 String encodeString = Base64.encodeBase64String(b);
	    	 
	    }
	    

}
