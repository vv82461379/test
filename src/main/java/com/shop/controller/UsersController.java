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
 * 在这里加了 @ResponseBody则springmvc的拼接跳转页面失效，因为默认返回html的格式。
 * 使用了@RequestBody的controller不能通过表单等方式请求，只能用json方式，get请求不能加@RequestBody.
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
	 * RestTemplate的get请求,只能传输String类型的     变量 （重要是变量RequestId！）  
	 */
	@RequestMapping("/testRestTemplateGet")
	public void testRestTemplateGet(){
		HttpHeaders headers = new HttpHeaders();

		String url = "http://localhost:8090/item/test?str=1";
		int integer = 1;
		String aaa = restTemplate.getForObject(url,String.class);
	}
	/**
	 * RestTemplate的post请求,请求的内容如果是多个参数 public @ResponseBody MyResponse processRequest(String RequestId, String count)
	 * 则用Map当做传入的值，但是只能传输String类型的     变量 （重要是变量RequestId！）
	 * 在调用的接口上必须加@ResponseBody，否则无法获得数据
	 */
	@RequestMapping("/testRestTemplatePost")
	public void testRestTemplatePost(){
		HttpHeaders headers = new HttpHeaders();

		String url = "http://localhost:8090/item/listing";
		String str = "lixiang";
		String aaa = restTemplate.postForObject(url,str,String.class);
	}
	/**
	 * RestTemplate的post请求通用形式（发送json），可以接受json,把对象转为json要用org.codehaus.jackson下的工具，这个错误少，如果用JSONObject转为字符串会有问题。
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
	        
	        ObjectMapper mapper = new ObjectMapper(); //转换器  
	        String jsonObj = JsonUtils.object2Json(itemDto);
	        ItemDto itemDto2 = JsonUtils.json2Object(jsonObj,ItemDto.class);
	        
	        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

	        String result = restTemplate.postForObject(url, formEntity, String.class);
		
		
	}
	
	
	/**
	 * 这是restful风格，@RequestMapping中的{xv}要与@PathVariable("xv")的xv相同，才能获取到，String id则不必相同，会自动将变量xv赋值给id。
	 * @param id
	 * @return
	 */
	  @RequestMapping("/test/{xv}")
	    public String queryByCondition(@PathVariable("xv") String id){
		  Long userId=Long.parseLong(id);
	        System.out.println(userId);
	        System.out.println("begin");
	        Users users = userService.queryById(userId);
	       //model不需要返回
//	        model.addAttribute("users", users);
	        return "selectStu";
	  }
	  
	  @RequestMapping("/save")
	  public void save(Users users){
		  userService.save(users);
	  }
	  
	  //通过form获取一条一条数据，按属性获取
	  @RequestMapping("/print1")
	  public void prit1(@RequestParam("id") Long id,Model model){
		  System.out.println(id);
	  }
	  
	//获取form中的属性，直接在postman中设置id  1  ,这里会自动匹配到users中
	  @RequestMapping("/print2")
	  public void prit2(Users user,Model model){
		  System.out.println(user.getId());
	  }
	  /**
	   * 获取json形式的对象,@RequestBody会根据请求头中header的Content-Type类型判断
	   * 在postman中header中放入Content-Type   application/json
	   * 在body的row中输入{"id":1}就会在对象中自动匹配id=1通过users.getId()获得.
	   * 解析json只能是bean对象才行，例如Users，如果是参数，例如userId等则获取不到前端的值。
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
	     * 锟津单碉拷 md5 锟斤拷锟斤拷模锟斤拷
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
	     * 锟斤拷转为Byte[]锟斤拷锟介，锟斤拷锟斤拷锟斤拷
	     */
	    private void transform(){
	    	 ByteArrayOutputStream out = new ByteArrayOutputStream();
//	    	 out.write(1);
	    	 byte[] b = out.toByteArray();
	    	 //base64锟斤拷锟斤拷 ,encodeString锟斤拷锟斤拷锟斤拷锟斤拷
	    	 String encodeString = Base64.encodeBase64String(b);
	    	 
	    }
	    

}
