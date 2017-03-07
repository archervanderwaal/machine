package com.baidu.serivce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dal.model.Server;
import com.baidu.exception.ConnectSessionFailedException;
import com.baidu.exception.CreateSessionFailedException;
import com.baidu.utils.HandleDataUtil;
import com.baidu.utils.JSchUtil;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author mayongbin01
 *         <p>
 *         create by mayongbin01 2017/01/22
 *         <p>
 *         Service, in order to execute shell for server
 *         <p>
 *         provide interface for controller
 */
@Service
public class ExecuteShellService {

    //command
    private static final String commands = ""
            + "echo tomcat;"
            + "ps -ef|grep tomcat;"
            + "echo mysql;"
            + "ps -ef|grep mysql;"
            + "echo nginx;"
            + "ps -ef|grep nginx;"
            + "echo redis;"
            + "ps -ef|grep redis;"
            + "echo solr;"
            + "ps -ef|grep solr;";
    //get log
    private static Logger logger = LoggerFactory.getLogger(ExecuteShellService.class);
    @Autowired
    private JSchUtil jSchUtil;
    @Autowired
    private HandleDataUtil handleDataUtil;

    /**
     * connect server to execute command
     *
     * @param server
     *
     * @return
     */
    public ArrayList<String> executeShell(Server server)
            throws CreateSessionFailedException, ConnectSessionFailedException {

        logger.info(
                "the serever that execute shell is : " + ReflectionToStringBuilder.toString(server));
        //execute shell results
        ArrayList<String> stdout = new ArrayList<>();

        //statement session
        Session session = null;
        //statement channel
        ChannelExec channel = null;
        //statement inpustream
        BufferedReader bufferedReader = null;
        try {
            //create a session to the server
            session = jSchUtil.createSession(server.getDestIp(), server.getDestLoginname(), server.getDestPassword
                    (), server.getDestPort());

            //if create session failed
            if (session == null) {
                throw new CreateSessionFailedException("Create session failed! maybe loginname or password == null! "
                        + "please check out it! ");
            }
            //set timeout
            session.connect(10000);
            //get the channel of the specified type
            channel = (ChannelExec) jSchUtil.openChannel(session, "exec");
            //set the command to execute
            channel.setCommand(commands);
            //connect channel
            channel.connect();
            //get the inputstream for channel
            bufferedReader = new
                    BufferedReader(new InputStreamReader(channel.getInputStream()));
            //one line result
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stdout.add(line);
            }

            logger.info("execute the shell result : " + StringUtils.join(stdout, "\r\n"));

        } catch (JSchException e) {
            //connect timed out maybe loginname or password is incorrent
            logger.debug("connect session failed !" + e.toString());
            throw new ConnectSessionFailedException("connect timed out! maybe loginname or password is incorrent! "
                    + "please check out it!");
        } catch (IOException e) {
            //throws exception information up
            throw new RuntimeException(e);

        } finally {
            //close resources
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
        //handle result data! and return object
        return stdout;
    }
}
