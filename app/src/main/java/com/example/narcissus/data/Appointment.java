package com.example.narcissus.data;

import java.io.Serializable;

public class Appointment implements Serializable {
    private String typeProcedure;
    private String typeAppointment;
    private String date;
    private String clinic;

    public String getTypeProcedure() {
        return typeProcedure;
    }

    public void setTypeProcedure(String typeProcedure) {
        this.typeProcedure = typeProcedure;
    }

    public String getTypeAppointment() {
        return typeAppointment;
    }

    public void setTypeAppointment(String typeAppointment) {
        this.typeAppointment = typeAppointment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}
