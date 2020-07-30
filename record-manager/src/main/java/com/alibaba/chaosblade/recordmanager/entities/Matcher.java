package com.alibaba.chaosblade.recordmanager.entities;

import javax.persistence.*;

/**
 * @author randika
 */
@Entity
@Table(name="matcher_information")
public class Matcher {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;

    private String experimentMatcher;

    private String value;

    public Matcher(String experimentMatcher, String value) {
        this.experimentMatcher = experimentMatcher;
        this.value = value;
    }

    public Matcher() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperimentMatcher() {
        return experimentMatcher;
    }

    public void setExperimentMatcher(String experimentMatcher) {
        this.experimentMatcher = experimentMatcher;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
