package com.shop.controller;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.model.Users;
import com.shop.service.UserService;

/**
 * 在这里加了 @ResponseBody则springmvc的拼接跳转页面失效，因为默认返回html的格式。 
 * @author garen_li
 *
 */

@Controller

public class UsersController {
	
	@Autowired
	private UserService userService;
	
	  @RequestMapping("/test")
	    public String queryByCondition(@RequestParam("id") String id,Model model){
		  Long userId=Long.parseLong(id);
	        System.out.println(userId);
	        System.out.println("begin");
	        Users users = userService.queryById(userId);
	       //model不需要返回
	        model.addAttribute("users", users);
	        return "selectStu";
	        
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
	   * @param users
	   * @param model
	   * @return
	   */
	  
	  @RequestMapping("/print")
	    public void queryByCondition(@RequestBody Users users,Model model){
			System.out.println(users.getId());
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
