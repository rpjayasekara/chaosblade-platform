package com.alibaba.chaosblade.api.controllers;

import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author randika
 */
@RestController
@RequestMapping("chaosblade/")
@CrossOrigin(origins = "http://localhost:4200")
public class HostController {

    @Autowired
    HostManager hostManager;

    @RequestMapping(value = "hosts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Host> getAllHosts(){

        return hostManager.getAllHosts();

    }

    @RequestMapping(value = "hosts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Host createHost(@RequestBody Host host){

        return hostManager.addHost(host);

    }

    @RequestMapping(value = "deletehost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Host> deleteHost(@RequestBody Host host){

        hostManager.removeHost(host.getHostID());
        return hostManager.getAllHosts();

    }

}
