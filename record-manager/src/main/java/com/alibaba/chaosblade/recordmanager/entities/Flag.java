package com.alibaba.chaosblade.recordmanager.entities;

import javax.persistence.*;

/**
 * @author randika
 */
@Entity
@Table(name="flag_information")
public class Flag {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;

    private String experimentFlag;

    private String value;

    public Flag(String experimentFlag, String value) {
        super();
        this.experimentFlag = experimentFlag;
        this.value = value;
    }

    public Flag() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperimentFlag() {
        return experimentFlag;
    }

    public void setExperimentFlag(String experimentFlag) {
        this.experimentFlag = experimentFlag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
