package com.baidu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.dal.model.Server;
import com.baidu.exception.ConnectSessionFailedException;
import com.baidu.exception.CreateSessionFailedException;
import com.baidu.serivce.ExecuteShellService;
import com.baidu.serivce.HandleDataService;
import com.baidu.serivce.ServerService;
import com.baidu.serivce.UpdateServerInfosService;

/**
 * @author mayongbin01
 *         <p>
 *         Created by mayongbin01 on 2017/1/17.
 *         <p>
 *         controller
 *         <p>
 *         process business logic
 */
@Controller
@RequestMapping("/")
public class ServerController {

    //logging
    private static Logger logger = LoggerFactory.getLogger(ServerController.class);

    //serverService
    @Autowired
    private ServerService serverService;

    //executeShellService
    @Autowired
    private ExecuteShellService executeShellService;

    //handleDataService
    @Autowired
    private HandleDataService handleDataService;

    //updateServerInfosService
    @Autowired
    private UpdateServerInfosService updateServerInfosService;

    /**
     * list all servers
     *
     * mapping /machine/servers
     *
     * @param model
     *
     * @return
     */
    @GetMapping("/servers")
    public String serverList(Model model) {

        ArrayList<Server> servers = (ArrayList<Server>) serverService.findAllServers();

        //add attribute to model
        model.addAttribute("servers", servers);

        return "index";
    }

    /**
     * find server information by id
     *
     * mapping /machine/server/{id}
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/server/{id}")
    public String getServer(@PathVariable int id, Model model) {

        Server server = serverService.findOneServerById(id);

        //add attribute to model
        model.addAttribute("server", server);

        return "serverinfo";
    }

    /**
     * add server to db
     *
     * mapping /machine/server/add
     * post request and param bind to a object
     *
     * @param server
     *
     * @param model
     *
     * @return
     */
    @PostMapping("/server/add")
    public String addServer(@ModelAttribute Server server, Model model)
            throws CreateSessionFailedException, ConnectSessionFailedException {
        logger.info(ReflectionToStringBuilder.toString(server));

        //determine whether the server can connect
        try {
            //execute shell and set serverInfos to server property
            server.setServerInfos(handleDataService.handleData(
                    executeShellService.executeShell(server)
            ));

        } catch (CreateSessionFailedException e) {
            //create session failed
            //logging
            logger.warn("CreateSessionFailedException " + e.toString());
            //add errorMessage to model
            model.addAttribute("errorMessage", "please provide correct server's ip loginname, password,"
                    + " and server's prot！");
            //error.jsp and errorMessage
            return "error";

        } catch (ConnectSessionFailedException e) {
            //connect session failed
            //logging
            logger.warn("ConnectSessionFailedException " + e.toString());
            //add errorMessage to model
            model.addAttribute("errorMessage", "connect server <em>failed</em>!"
                    + " maybe username or password is incorrent!");
            //error.jsp and errorMessage
            return "error";
        } catch (RuntimeException e) {
            //IoException
            //logging
            logger.warn("RuntimeException " + e.toString());
            model.addAttribute("errorMessage", "server has a <em>error</em>！");
            //error.jsp view
            return "error";
        }

        if ((server = serverService.addServer(server)) == null) {
            model.addAttribute("errorMessage", "server has a <em>error</em>！");
            //error.jsp
            return "error";
        } else {
            return "redirect:/server/" + server.getId();
        }
    }

    /**
     * get add.jsp
     *
     * mapping /machine/server/add
     * get request and no param
     *
     * @return
     */
    @GetMapping("/server/add")
    public String addServer() {
        //return add.jsp
        return "add";
    }

    /**
     * get server by destType
     *
     * mapping /machine/server
     * get request and provide destType
     *
     * @param destType
     *
     * @return
     */
    @GetMapping("/server")
    public String getServerByDestType(@RequestParam String destType, Model model) {
        //get server by destType
        ArrayList<Server> servers = (ArrayList<Server>) serverService.getServerByDestType(destType);

        //add attribute to model
        model.addAttribute("servers", servers);

        //index.jsp
        return "index";
    }

    /**
     * delete server by id
     *
     * mapping /machine/server/delete/{id}
     * get request and Restful url
     * pathVariable
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/server/delete/{id}")
    public String deleteServer(@PathVariable int id) {
        //delete server by id
        serverService.deleteServer(id);
        //return index.jsp view
        return "redirect:/servers";
    }

    /**
     * update serevrInfos for each server
     *
     * @return
     */
    @RequestMapping("/servers/update")
    public String updateServers(Model model, HttpSession session) {

        List<Server> canNotConnectServers = updateServerInfosService.autoUpdate();
        model.addAttribute("servers", serverService.findAllServers());
        model.addAttribute("FailServers", canNotConnectServers);
        return "index";
    }

    /**
     * edit server
     *
     * @param model
     *
     * @param id
     *
     * @return
     */
    @RequestMapping("/server/editor/{id}")
    public String editorServer(Model model, @PathVariable int id) {
        Server server = serverService.findOneServerById(id);
        model.addAttribute("server", server);
        return "editor";
    }

    @RequestMapping("/server/edit")
    public String editServer(Model model, @ModelAttribute Server server) {
        //修改服务器信息
        Server s = serverService.findServerByIp(server.getDestIp());
        s.setDestPassword(server.getDestPassword());
        s.setDestLoginname(server.getDestLoginname());
        s.setDestType(server.getDestType());
        s.setDestPort(server.getDestPort());
        serverService.addServer(s);
        return "redirect:/servers";
    }
}
