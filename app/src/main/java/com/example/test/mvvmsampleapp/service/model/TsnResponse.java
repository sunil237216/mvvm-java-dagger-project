package com.example.test.mvvmsampleapp.service.model;

public class TsnResponse {

    /**
     * TSN : 5M218L00002
     * Remark : test
     * Status : 2
     * ModelNumber : 5930N
     * Phase1Status : 1
     * Id : 6
     * IsActive : true
     */

    private String TSN;
    private String Remark;
    private int Status;
    private String ModelNumber;
    private int Phase1Status;
    private int Id;
    private boolean IsActive;

    public String getTSN() {
        return TSN;
    }

    public void setTSN(String TSN) {
        this.TSN = TSN;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    public void setModelNumber(String ModelNumber) {
        this.ModelNumber = ModelNumber;
    }

    public int getPhase1Status() {
        return Phase1Status;
    }

    public void setPhase1Status(int Phase1Status) {
        this.Phase1Status = Phase1Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }
}
