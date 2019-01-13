package com.can.minidoctor.api.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 17:13 2019/1/13
 */
public class SelfResp implements Serializable {
    private List<DateMentLisResp> myDates;
    private List<DateMentLisResp> todayList;
    private int admin;

    public List<DateMentLisResp> getMyDates() {
        return myDates;
    }

    public void setMyDates(List<DateMentLisResp> myDates) {
        this.myDates = myDates;
    }

    public List<DateMentLisResp> getTodayList() {
        return todayList;
    }

    public void setTodayList(List<DateMentLisResp> todayList) {
        this.todayList = todayList;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
