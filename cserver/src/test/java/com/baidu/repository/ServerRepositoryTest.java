package com.baidu.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.CserverApplication;
import com.baidu.dal.dao.ServerRepository;
import com.baidu.dal.model.Server;
import com.baidu.dal.model.ServerInfo;

/**
 *  <p>Created by mayongbin01 on 2017/1/16.</p>
 *
 * @author mayongbin01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = CserverApplication.class)
public class ServerRepositoryTest {

    @Resource
    private ServerRepository serverRepository;

    private static Logger logger = LoggerFactory.getLogger(ServerRepositoryTest.class);

    @Test
    public void test() {

        List<Server> servers = serverRepository.findByDestType(0 + "");
        for (Server server : servers) {
            System.out.println("-----" + server);
        }
    }

    @Test
    @Transactional
    public void updateServer() {
        serverRepository.updateDestType("0", 3);
    }


    @Test
    public void setServer() {
        Server server = new Server();
        server.setDestType("88888");
        server.setDestIp("sssssss");
        server.setDestPort(22);
        ArrayList<ServerInfo> serverInfos = new ArrayList<>();
        serverInfos.add(new ServerInfo("ss", "ss"));
        server.setServerInfos(serverInfos);
        server.setDestPassword("sssss");
        serverRepository.save(server);
    }
}
