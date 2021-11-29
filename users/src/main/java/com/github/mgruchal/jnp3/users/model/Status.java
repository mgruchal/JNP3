package com.github.mgruchal.jnp3.users.model;

import java.util.Calendar;
import java.util.Date;

public class Status {
    Date currentDate;
    String host;

    public Status() {
        this.currentDate = Calendar.getInstance().getTime();
        this.host = System.getenv("HOST");
        if (this.host == null) {
            this.host = "";
        }
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public String getHost() {
        return host;
    }
}
