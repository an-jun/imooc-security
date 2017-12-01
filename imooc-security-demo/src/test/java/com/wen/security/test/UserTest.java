package com.wen.security.test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
public class UserTest extends BaseTest{
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	//@Test
	public void whenQuerySuccess() throws Exception {
		String result=mockMvc.perform(MockMvcRequestBuilders.get("/user")
					.param("username", "小明")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	//@Test
	public void getUserInfo() throws Exception {
		String result=mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	/**
	 * 用户创建请求
	 */
	//@Test
	public void whenCreateSuccess() {
		String content="{\"username\":\"tom\",\"id\":1}";
		try {
			String result=mockMvc.perform(MockMvcRequestBuilders.post("/user")
					.param("username", "小明")
					.param("id", "1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					)
			 		.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
					.andReturn().getResponse().getContentAsString();
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//@Test  //put  请求做修改  
		public void whenUpdateSuccess() {
			//传入后一年的时间
			Date date=new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			String content="{\"username\":\"tom\",\"id\":1,\"birthday\":"+date.getTime()+"}";
			try {
				String result=mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
				 		.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
						.andReturn().getResponse().getContentAsString();
				System.out.println(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		//@Test
		public void whenUploadSuccess() {
			try {

			mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
					.file(new MockMultipartFile("file", "test.txt","mutipart/from-data","hello world".getBytes()))
					)
			 		.andExpect(MockMvcResultMatchers.status().isOk());
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	 
}
