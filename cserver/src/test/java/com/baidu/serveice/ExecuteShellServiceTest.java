package com.baidu.serveice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baidu.CserverApplication;
import com.baidu.dal.model.Server;
import com.baidu.serivce.ExecuteShellService;

/**
 *  <p>Created by mayongbin01 on 2017/1/16.</p>
 *
 * @author mayongbin01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CserverApplication.class)
@WebAppConfiguration
public class ExecuteShellServiceTest {

    @Autowired
    private ExecuteShellService executeShellService;

    @Test
    public void executeShellTest() {
        Server server = new Server();
        server.setDestIp("stormmaybin.cn");
        server.setDestLoginname("ubuntu");
        server.setDestPassword("StormMaybin@");
        server.setDestPort(22);

        //ArrayList<String> stdout = executeShellService.executeShell(server);

//        for (String str : stdout) {
//            System.out.println(str);
//        }
    }
}
