package com.wen.security.test;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
public class ApiTest extends BaseTest{
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
		//@Test  //put  请求做修改  
		public void whenUpdateSuccess() {
			//\"sourceSyetem\":syetem,\"leUniversal\":universal,\"mrcUniversal\":universal
			String content="{\"fiscalYear\":\"Year\",\"perion\":\"ion\",\"sourceSyetem\":\"syetem\",\"leUniversal\":\"\",\"mrcUniversal\":\"universal\"}";
			try {
				mockMvc.perform(MockMvcRequestBuilders.post("/tm2copa")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
				 		.andExpect(MockMvcResultMatchers.status().isOk());
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
		//@Test  //put  请求做修改  
		public void whenSuccess() {
			//\"sourceSyetem\":syetem,\"leUniversal\":universal,\"mrcUniversal\":universal
			String content="{\"userId\":\"Id\",\"universalLedger\":\"Ledger\",\"leUniversal\":\"Universal\",\"leLocal\":\"Local\",\"mrcUniversal\":\"mUniversal\",\"fiscalYear\":\"Year\",\"period\":\"riod\",\"sourceSystem\":\"System\",\"accountType\":\"acType\",\"accountsTo\":\"\",\"userInput\":\"Input\",\"globalFunctionalArea\":\"\"}";
			try {
				mockMvc.perform(MockMvcRequestBuilders.post("/trialbalance")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
				 		.andExpect(MockMvcResultMatchers.status().isOk());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
