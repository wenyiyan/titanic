package com.ezops.assignments.titanic.model;

import com.ezops.assignments.titanic.utils.JSONUtils;

import javax.persistence.*;
import java.io.Serializable;



/**
 * Creating Persistence Layer passenger
 * @author yao.chen
 */

@Entity
@Table(name ="titanic_passengers")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column
    private int passengerId;

    @Column
    private String survived;

    @Column
    private Integer pclass;

    @Column
    private String name;

    @Column
    private String sex;

    @Column
    private String age;

    @Column
    private Integer sibSp;

    @Column
    private Integer parch;

    @Column
    private String ticket;

    @Column
    private double fare;

    @Column
    private String cabin;

    @Column
    private String embarked;

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getSurvived() {
        return survived;
    }

    public void setSurvived(String survived) {
        this.survived = survived;
    }

    public Integer getPclass() {
        return pclass;
    }

    public void setPclass(Integer pclass) {
        this.pclass = pclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getSibSp() {
        return sibSp;
    }

    public void setSibSp(Integer sibSp) {
        this.sibSp = sibSp;
    }

    public Integer getParch() {
        return parch;
    }

    public void setParch(Integer parch) {
        this.parch = parch;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }




    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
