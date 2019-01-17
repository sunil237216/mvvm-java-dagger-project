package com.example.test.mvvmsampleapp.service.model;

import java.util.List;
import java.util.Set;

public class StatusResponse {
    /**
     * TSN : s
     * ModelNumber : sample string 2
     * CheckListData : [{"CheckListId":3,"Remark":"sample string 5","Status":1,"OtherRemarks":"sample string 6"},{"CheckListId":3,"Remark":"sample string 5","Status":1,"OtherRemarks":"sample string 6"}]
     */

    private String TSN;
    private String ModelNumber;
    private Set<CheckListDataBean> CheckListData;



    public String getTSN() {
        return TSN;
    }

    public void setTSN(String TSN) {
        this.TSN = TSN;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    public void setModelNumber(String ModelNumber) {
        this.ModelNumber = ModelNumber;
    }

    public Set<CheckListDataBean> getCheckListData() {
        return CheckListData;
    }

    public void setCheckListData(Set<CheckListDataBean> CheckListData) {
        this.CheckListData = CheckListData;
    }

    public static class CheckListDataBean {
        /**
         * CheckListId : 3
         * Remark : sample string 5
         * Status : 1
         * OtherRemarks : sample string 6
         */

        private int CheckListId;
        private String Remark;
        private int Status;
        private String OtherRemarks;

        @Override
        public int hashCode() {
            System.out.println("In hashcode");
            return this.getCheckListId();
        }

        @Override
        public boolean equals(Object obj) {
            CheckListDataBean e = null;
            if(obj instanceof CheckListDataBean){
                e = (CheckListDataBean) obj;
            }
            System.out.println("In equals");
            if(this.getCheckListId() == e.getCheckListId()){
                e.setStatus(this.getStatus());
                e.setRemark(this.getRemark());
                return true;
            }

            else {
               // e.setStatus(this.getStatus());
                //e.setRemark(this.getRemark());
                return false;
            }
        }

        public int getCheckListId() {
            return CheckListId;
        }

        public void setCheckListId(int CheckListId) {
            this.CheckListId = CheckListId;
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

        public String getOtherRemarks() {
            return OtherRemarks;
        }

        public void setOtherRemarks(String OtherRemarks) {
            this.OtherRemarks = OtherRemarks;
        }
    }
}
