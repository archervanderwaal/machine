package com.baidu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * @author mayongbin01
 *         <p>
 *         Created by mayongbin01 on 2017/1/18.
 *         <p>
 *         util, in order to provide service for service
 */
@Component
public class JSchUtil {

    //logging
    private static Logger logger = LoggerFactory.getLogger(JSchUtil.class);

    //statement jsch object
    private static JSch jSch = new JSch();

    /**
     * Create a Session
     *
     * @param destIp
     * @param destUsername
     * @param destPassword
     * @param destPort
     *
     * @return
     */
    public Session createSession(String destIp, String destUsername, String destPassword, int destPort)
            throws JSchException {

        //statement a session for server
        Session session;

        session = jSch.getSession(destUsername, destIp, destPort);

        assert session != null;
        session.setPassword(destPassword);
        //set userInfo for session
        session.setUserInfo(new UserInfo() {
            @Override
            public String getPassphrase() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public boolean promptPassword(String s) {
                return false;
            }

            @Override
            public boolean promptPassphrase(String s) {
                return false;
            }

            @Override
            public boolean promptYesNo(String s) {
                return true;
            }

            @Override
            public void showMessage(String s) {

            }
        });
        return session;
    }

    /**
     * create a specified session
     *
     * @param session
     * @param type
     *
     * @return
     *
     * @throws JSchException
     */
    public Channel openChannel(Session session, String type) throws JSchException {
        return session.openChannel(type);
    }
}
