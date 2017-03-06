package com.baidu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baidu.CserverApplication;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 *  <p>Created by mayongbin01 on 2017/1/16.</p>
 *
 * @author mayongbin01
 */

@SpringApplicationConfiguration(classes = CserverApplication.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JSchUtilTest {

    @Resource
    private JSchUtil jSchUtil;

    @Test
    public void creatSessionTest() throws JSchException, IOException {

        Session session = jSchUtil.createSession("stormmaybin.cn", "ubuntu", "StormMaybin@", 22);

        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");

        channel.setInputStream(null);

        BufferedReader input = new BufferedReader(new InputStreamReader(
                channel.getInputStream()));
        channel.setCommand("ps auxw");
        channel.connect();
        ArrayList<String> stdout = new ArrayList<String>();

        String line = null;
        while ((line = input.readLine()) != null) {
            stdout.add(line);
        }

        for (String str : stdout) {
            System.out.println(str);
        }
    }
}
