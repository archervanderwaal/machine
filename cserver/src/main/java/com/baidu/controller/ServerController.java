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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.dal.model.Server;
import com.baidu.exception.ConnectSessionFailedException;
import com.baidu.exception.CreateSessionFailedException;
import com.baidu.exception.HostNameExistException;
import com.baidu.serivce.ExecuteShellService;
import com.baidu.serivce.HandleDataService;
import com.baidu.serivce.ServerService;
import com.baidu.serivce.UpdateServerInfosService;

/**
 *         <p>
 *         Created by mayongbin01 on 2017/1/17.
 *         <p>
 *         controller
 *         <p>
 *         process business logic.
 */
@Controller
@RequestMapping("/")
public class ServerController {

    private static Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private ServerService serverService;

    @Autowired
    private ExecuteShellService executeShellService;

    @Autowired
    private HandleDataService handleDataService;

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
    public String serverList(Model model, HttpSession session) {
        List<Server> servers = serverService.findAllServers();
        List<Server> canNotConnectServers = (List<Server>) session.getAttribute("failServers");
        if (!CollectionUtils.isEmpty(canNotConnectServers)) {
            for (Server server : canNotConnectServers) {
                if (!CollectionUtils.isEmpty(servers)) {
                    if (servers.contains(server)) {
                        servers.remove(server);
                    }
                }
            }
        }
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
            throws CreateSessionFailedException, ConnectSessionFailedException, HostNameExistException {
        logger.info(ReflectionToStringBuilder.toString(server));
        if (serverService.findServerByIp(server.getDestIp()) != null) {
            model.addAttribute("errorMessage", "the " + server.getDestIp() + " has existed.");
            return "error";
        }
        try {
            server.setServerInfos(handleDataService.handleData(
                    executeShellService.executeShell(server)
            ));

        } catch (CreateSessionFailedException e) {
            logger.warn("CreateSessionFailedException " + e.toString());
            model.addAttribute("errorMessage", "please provide correct server's ip loginname, password,"
                    + " and server's prot！");
            return "error";

        } catch (ConnectSessionFailedException e) {
            logger.warn("ConnectSessionFailedException " + e.toString());
            model.addAttribute("errorMessage", "connect server <em>failed</em>!"
                    + " maybe username or password is incorrent!");
            return "error";
        } catch (RuntimeException e) {
            logger.warn("RuntimeException " + e.toString());
            model.addAttribute("errorMessage", "server has a <em>error</em>！");
            return "error";
        }

        if ((server = serverService.addServer(server)) == null) {
            model.addAttribute("errorMessage", "server has a <em>error</em>！");
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
    public String getServerByDestType(@RequestParam String destType, Model model, HttpSession session) {
        ArrayList<Server> servers = (ArrayList<Server>) serverService.getServerByDestType(destType);
        List<Server> canNotConnectServers = (List<Server>) session.getAttribute("failServers");
        if (!CollectionUtils.isEmpty(canNotConnectServers) && !CollectionUtils.isEmpty(servers)) {
            for (Server server : canNotConnectServers) {
                if (!servers.contains(server)) {
                    canNotConnectServers.remove(server);
                } else {
                    servers.remove(server);
                }
            }
        }
        model.addAttribute("servers", servers);
        session.setAttribute("failServers", canNotConnectServers);
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
    public String deleteServer(@PathVariable int id, HttpSession session, Model model) {
        Server server = serverService.findOneServerById(id);
        serverService.deleteServer(id);
        List<Server> canNotConnectServers = (List<Server>) session.getAttribute("failServers");
        if (canNotConnectServers.contains(server)) {
            canNotConnectServers.remove(server);
        }
        session.setAttribute("failServers", canNotConnectServers);
        return "redirect:/servers";
    }

    /**
     * update serevrInfos for each server
     *
     * @return
     */
    @RequestMapping("/servers/update")
    public String updateServers(HttpSession session, Model model) {

        List<Server> canNotConnectServers = updateServerInfosService.autoUpdate();
        session.setAttribute("failServers", canNotConnectServers);
        return "redirect:/servers";
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

    @PostMapping(value = "/server/edit")
    public String editServer(Model model, @ModelAttribute Server server, HttpSession session) {
        List<Server> canNotConnectServers = (List<Server>) session.getAttribute("failServers");
        /*update server*/
        Server s = serverService.findServerByIp(server.getDestIp());
        s.setDestPassword(server.getDestPassword());
        s.setDestLoginname(server.getDestLoginname());
        s.setDestType(server.getDestType());
        s.setDestPort(server.getDestPort());
        Server server1 = serverService.addServer(s);
        if (canNotConnectServers.contains(s)) {
            canNotConnectServers.remove(s);
            canNotConnectServers.add(server1);
        }
        return "redirect:/servers";
    }
}
