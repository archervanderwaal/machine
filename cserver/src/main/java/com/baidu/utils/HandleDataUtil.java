package com.baidu.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baidu.dal.model.ServerInfo;

/**
 * @author mayongbin01
 *         <p>
 *         Created by mayongbin01 on 2017/1/18.
 *         <p>
 *         util, in order to process execute shell result data
 */
@Component
public class HandleDataUtil {

    //get log
    private Logger logger = LoggerFactory.getLogger(HandleDataUtil.class);

    /**
     * handle execute shell result data , and
     * save information into List<ServerInfo>
     *     collection</>
     *
     * @param stdout
     */
    public List<ServerInfo> handleData(ArrayList<String> stdout) {

        // logging
        for (String string : stdout) {
            logger.info(string);
        }

        ArrayList<ServerInfo> serverInfos = new ArrayList<>();

        //service name
        String key = "";

        //content for the service
        String value = "";

        for (String string : stdout) {
            //replaceAll
            string = string.replaceAll(" +", " ");
            // get the service name
            if (!string.contains(" ")) { //tomcat mysql negix redis solr
                key = string;
            } else {
                //is a running process, get it!
                if (!string.contains("00:00:00")) {
                    //if the service name equals tomcat
                    if (key.equals("tomcat")) {
                        //spilt with spaces
                        String[] datas = string.split(" ");
                        for (String data1 : datas) {
                            //get the project name of the tomcat deploying
                            if (data1.startsWith("-Dcatalina.home=")) {
                                //spilt with "/"
                                String[] data = data1.split("/");
                                /*
                                *get the project name of the tomcat deploying.
                                * if has more than one project, spilt with "/"
                                */
                                value += data[data.length - 1] + "/";
                            }
                        }
                    } else {
                        //add serverInfo
                        serverInfos.add(new ServerInfo(key, key));
                    }
                }
            }
        }
        //add tomcat service to serverInfo
        serverInfos.add(new ServerInfo("tomcat", value));

        return serverInfos;
    }
}
