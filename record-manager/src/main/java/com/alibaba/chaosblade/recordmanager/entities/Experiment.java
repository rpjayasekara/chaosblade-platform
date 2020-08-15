package com.alibaba.chaosblade.recordmanager.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author randika
 */
@Entity
@Table(name="experiment_information")
public class Experiment {

    @Id
    private String uid;

    private String target;

    private String action;

    private String status;

    private Date dateCreated;

    private boolean isDestroyed;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Host host;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Matcher> matchers;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Flag> flags;

    public Experiment(String uid, String target, String action, String status, Date dateCreated, Host host, List<Matcher> matchers, List<Flag> flags, boolean isDestroyed) {
        this.uid = uid;
        this.target = target;
        this.action = action;
        this.status = status;
        this.dateCreated = dateCreated;
        this.host = host;
        this.matchers = matchers;
        this.flags = flags;
        this.isDestroyed = isDestroyed;
    }

    public Experiment() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public List<Matcher> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<Matcher> matchers) {
        this.matchers = matchers;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}
