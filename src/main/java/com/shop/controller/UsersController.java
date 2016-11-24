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
 * ��������� @ResponseBody��springmvc��ƴ����תҳ��ʧЧ����ΪĬ�Ϸ���html�ĸ�ʽ�� 
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
	       //model����Ҫ����
	        model.addAttribute("users", users);
	        return "selectStu";
	        
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
	   * @param users
	   * @param model
	   * @return
	   */
	  
	  @RequestMapping("/print")
	    public void queryByCondition(@RequestBody Users users,Model model){
			System.out.println(users.getId());
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
