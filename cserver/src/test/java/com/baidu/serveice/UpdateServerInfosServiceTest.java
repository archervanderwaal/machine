package com.baidu.serveice;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baidu.CserverApplication;
import com.baidu.serivce.UpdateServerInfosService;

/**
 * <p>Created by mayongbin01 on 2017/1/16.</p>
 *
 * @author mayongbin01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CserverApplication.class)
@WebAppConfiguration
public class UpdateServerInfosServiceTest {

    @Resource
    private UpdateServerInfosService updateServerInfosService;

    @Test
    public void test() {
        updateServerInfosService.autoUpdate();
    }
}
