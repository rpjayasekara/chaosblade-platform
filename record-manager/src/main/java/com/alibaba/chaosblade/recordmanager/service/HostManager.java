package com.alibaba.chaosblade.recordmanager.service;

import com.alibaba.chaosblade.recordmanager.dao.HostDAO;
import com.alibaba.chaosblade.recordmanager.entities.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author randika
 */
@Service
public class HostManager {

    @Autowired
    HostDAO hostDAO;

    @Transactional
    public Host addHost(Host host){

        return hostDAO.save(host);

    }

    @Transactional
    public void removeHost(int id){

        hostDAO.deleteById(id);

    }

    public List<Host> getAllHosts(){

        return hostDAO.findAll();

    }

    public Host getHostById(int id){

        Optional<Host> host = hostDAO.findById(id);

        if(host.isPresent()){
            return host.get();
        } else {
            return null;
        }

    }



}
