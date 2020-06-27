package com.codenation.eventlog;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.github.jsontemplate.JsonTemplate;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class EventLogControllerAndOauthTests {

    @Autowired
    private WebApplicationContext wac; 
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
       this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
         .addFilter(springSecurityFilterChain).build();
    }
    
    private String getAccessToken(String username, String password) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        
        ResultActions result 
        = mockMvc.perform(post("/oauth/token")
          .params(params)
          .with(httpBasic("admin","admin123"))
          .accept("application/json;charset=UTF-8"))
          .andExpect(status().isOk())
          .andExpect(content().contentType("application/json;charset=UTF-8"));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
    
    @Test
    public void whenNoTokenGiven() throws Exception {
    	mockMvc.perform(get("/log"))
    		      .andExpect(status().isUnauthorized());			      
    }
    
    @Test
    public void whenHasToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	mockMvc.perform(get("/log")
    			.header("Authorization", "Bearer " + accessToken))
    		      .andExpect(status().isOk());			      
    }
    
    @Test
    public void whenPerformHttpPostEventLogObjectWithoutToken() throws Exception {
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	mockMvc.perform(post("/log")
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType))
    		      .andExpect(status().isUnauthorized());			      
    }
    
    @Test
    public void whenPerformHttpPostEventLogObjectWithNullRequiredFieldsAndWithToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType))
    		      .andExpect(status().isBadRequest());			      
    }
    
    @Test
    public void whenPerformHttpPostEventLogObjectWithToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType))
    		      .andExpect(status().isOk());			      
    }
    
    @Test
    public void whenPerformHttpGetLogByIdWithoutToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	//just creating the object
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType));
    	
    	mockMvc.perform(get("/log/25")
    			.accept(contentType))
    		      .andExpect(status().isUnauthorized());			      
    }
    
    @Test
    public void whenPerformHttpGetLogByIdWithToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	//just creating the object
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType));
    	
    	mockMvc.perform(get("/log/25")
    			.header("Authorization", "Bearer " + accessToken)
    			.accept(contentType))
    		      .andExpect(status().isOk());			      
    }

    @Test
    public void whenPerformHttpGetLogByLevelWithoutToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	//just creating the object
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType));
    	
    	mockMvc.perform(get("/log/level/ERROR")
    			.accept(contentType))
    		      .andExpect(status().isUnauthorized());			      
    }
    
    @Test
    public void whenPerformHttpGetLogByLevelWithToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	//just creating the object
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType));
    	
    	mockMvc.perform(get("/log/level/ERROR")
    			.accept(contentType)
    				.header("Authorization", "Bearer " + accessToken)
    				.accept(contentType))
    		      .andExpect(status().isOk());			      
    }
    
    @Test
    public void whenPerformHttpGetLogOrderByWithoutToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	//just creating the object
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType));
    	
    	mockMvc.perform(get("/log/quantity/gt-2")
    			.accept(contentType))
    		      .andExpect(status().isUnauthorized());			      
    }
    
    @Test
    public void whenPerformHttpGetLogOrderByWithToken() throws Exception {
    	String accessToken = getAccessToken("teste@teste.com","teste123");
    	String eventLogJSON = new JsonTemplate(new StringBuilder()
    			.append("{")
    			.append("level : ERROR,")
    			.append("eventDescription : @s,")
    			.append("eventLog : @s,")
    			.append("origin : @ip,")
    			.append("quantity : @f")
    			.append("}").toString()).prettyString();
    	String contentType = "application/json;charset=UTF-8";
    	
    	//just creating the object
    	mockMvc.perform(post("/log")
    			.header("Authorization", "Bearer " + accessToken)
    			.contentType(contentType)
    			.content(eventLogJSON)
    			.accept(contentType));
    	
    	mockMvc.perform(get("/log/quantity/gt-2")
    			.accept(contentType)
    				.header("Authorization", "Bearer " + accessToken)
    				.accept(contentType))
    		      .andExpect(status().isOk());
    	
    	mockMvc.perform(get("/log/origin/like-127")
    			.accept(contentType)
    				.header("Authorization", "Bearer " + accessToken)
    				.accept(contentType))
    		      .andExpect(status().isOk());
    	
    	mockMvc.perform(get("/log/eventDescription/like-te")
    			.accept(contentType)
    				.header("Authorization", "Bearer " + accessToken)
    				.accept(contentType))
    		      .andExpect(status().isOk());	
    }
    
}
