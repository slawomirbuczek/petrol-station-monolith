package com.pk.petrolstationmonolith.models.monitoring;

import java.sql.Date;

public class RequestParametersBetweenDates {

    private Date from;
    private Date to;

    public RequestParametersBetweenDates() {
    }

    public RequestParametersBetweenDates(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

}
