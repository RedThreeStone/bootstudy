package it.lei;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

public class UserActionTest extends BaseTest{
    @Test
    @Transactional
    public void addUserTest() throws  Exception{
        String userJson="{\"username\":\"刘明\",\"password\":\"a7633050\",\"sex\":\"男\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/newUserAction/addUser").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.username").value("刘明"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public  void CreatePassword(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}
