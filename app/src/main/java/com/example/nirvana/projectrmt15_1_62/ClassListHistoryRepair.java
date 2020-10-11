package com.example.nirvana.projectrmt15_1_62;

import java.sql.Timestamp;

public class ClassListHistoryRepair {

    String nameTechnician;
    String department;
    String rubberF;
    String rubberB;
    String priceF;
    String priceB;

    String dateRepair;
    String statusRepair;

    public ClassListHistoryRepair(String rubberF,String rubberB,String priceF,String priceB){

        this.rubberF=rubberF;
        this.rubberB=rubberB;
        this.priceF=priceF;
        this.priceB=priceB;

    }
    public ClassListHistoryRepair(String nameTechnician, String department, String dateRepair){
        this.nameTechnician=nameTechnician;
        this.department=department;
        this.dateRepair=dateRepair;

    }

    public ClassListHistoryRepair(String nameTechnician, String department, String dateRepair,String rubberF,String priceF,String rubberB,String priceB,String statusRepair){
        this.nameTechnician=nameTechnician;
        this.department=department;
        this.dateRepair=dateRepair;
        this.rubberF=rubberF;
        this.rubberB=rubberB;
        this.priceF=priceF;
        this.priceB=priceB;
        this.statusRepair=statusRepair;

    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRubberF() {
        return rubberF;
    }

    public void setRubberF(String rubberF) {
        this.rubberF = rubberF;
    }

    public String getRubberB() {
        return rubberB;
    }

    public void setRubberB(String rubberB) {
        this.rubberB = rubberB;
    }

    public String getPriceF() {
        return priceF;
    }

    public void setPriceF(String priceF) {
        this.priceF = priceF;
    }

    public String getPriceB() {
        return priceB;
    }

    public void setPriceB(String priceB) {
        this.priceB = priceB;
    }

    public String getDateRepair() {
        return dateRepair;
    }

    public void setDateRepair(String dateRepair) {
        this.dateRepair = dateRepair;
    }

    public String getstatusRepair() {
        return statusRepair;
    }

    public void setstatusRepair(String statusRepair) {
        this.statusRepair = statusRepair;
    }



}
