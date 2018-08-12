package it.lei;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class BaseTest {
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Before
    public void  initMock(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

}
