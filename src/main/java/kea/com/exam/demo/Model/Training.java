package kea.com.exam.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Training {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String endTime;
    private String startime;
    private String note;

    public Training() {

    }

    public Training(int id, Date date, String endTime, String startime, String note) {
        this.id = id;
        this.date = date;
        this.endTime = endTime;
        this.startime = startime;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}


