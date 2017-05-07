import com.yangjun.controller.IndexController;
import com.yangjun.dao.mybatis.mapper.AccountInfoMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by admin on 2017/2/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { test.class })
@AutoConfigureMockMvc
public class test {

        private MockMvc mvc;
        @Autowired
        private AccountInfoMapper accountInfoMapper;
        @Test
        public void test1(){
            System.out.println(accountInfoMapper.selectByPrimaryKey(1));
        }

        @Before
        public void before(){
            this.mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
        }

        @Test
        public void conetxtLoads() throws Exception{
            RequestBuilder req = get("/api/index/");
            mvc.perform(req).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        }

}
