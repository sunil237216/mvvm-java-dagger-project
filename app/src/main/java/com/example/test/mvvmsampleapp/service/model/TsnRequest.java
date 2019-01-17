package com.example.test.mvvmsampleapp.service.model;

public class TsnRequest {
    private String TSN;
    private String Remark;
    private int Status;

    public String getTSN() {
        return TSN;
    }

    public String getRemark() {
        return Remark;
    }

    public int getStatus() {
        return Status;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    private String ModelNumber;

    public TsnRequest(String TSN, String remark, int status,String ModelNumber) {
        this.TSN = TSN;
        this.Remark = remark;
        this.Status = status;
        this.ModelNumber = ModelNumber;
    }
}
