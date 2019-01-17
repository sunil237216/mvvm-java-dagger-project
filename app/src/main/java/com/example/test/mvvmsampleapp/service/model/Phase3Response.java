package com.example.test.mvvmsampleapp.service.model;

import java.util.List;

public class Phase3Response {


    /**
     * Phase1Status : 1
     * Phase2Status : 2
     * CheckLists : [{"Description":"Completeness of Gearbox assembly as per model","RemarkList":["MODEL","LHD LEVER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"MODEL","Id":1,"IsActive":false},{"Description":"Proper mounting of gear box on the fixture","RemarkList":["FIXTURE","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"FIXTURE","Id":2,"IsActive":false},{"Description":"Visual inspection for damages / appearance","RemarkList":["SPEEDO SENSOR","DUST","RLS","GNS","LEVER","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"SPEEDO SENSOR","Id":3,"IsActive":false},{"Description":"Fitment of across flat (1) M17 nut / (2) Bolt on lever","RemarkList":["(1)MISSING","(2)MISSING","(2)LOOSE","(2)NO MARKING","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"(1)MISSING","Id":4,"IsActive":false},{"Description":"Fitment of (1) breather on lever (2) tie clip","RemarkList":["(1)MISSING","(1)DAMAGE","(2)MISSING","(2)DAMAGE","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":2,"Remarks":"(1)MISSING","Id":5,"IsActive":false},{"Description":"1D sticker on front housing","RemarkList":["PRINT","MISSING","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"PRINT","Id":6,"IsActive":false},{"Description":"TM QR code model","RemarkList":["PRINT","MISSING","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"PRINT","Id":7,"IsActive":false},{"Description":"Biasing of the lever in","RemarkList":["1st /2sd","5th / Rev","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":2,"Remarks":"1st /2sd","Id":8,"IsActive":false},{"Description":"Shifting hard / sticky","RemarkList":["1st","2nd","3rd","4th","5th","Rev."],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"1st","Id":9,"IsActive":false},{"Description":"Gear not engaging","RemarkList":["1st","2nd","3rd","4th","5th","Rev."],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"Rev.","Id":10,"IsActive":false},{"Description":"Gear dis engaging hard","RemarkList":["1st","2nd","3rd","4th","5th","Rev."],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"Rev.","Id":11,"IsActive":false},{"Description":"5th to rev lock function","RemarkList":["DIRECT SHIFT"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":2,"Remarks":"DIRECT SHIFT","Id":12,"IsActive":false},{"Description":"Reverse (1) lamp switch & (2) cap","RemarkList":["(1) MISSING","(1) DAMAGE","(2) CAP MISSING","(2)DAMAGE","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":2,"Remarks":"(1) MISSING","Id":13,"IsActive":false},{"Description":"oil seal protective cap on output shaft","RemarkList":["MISSING","DAMAGE","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"MISSING","Id":14,"IsActive":false},{"Description":"M.D Gear Freeness in neutral / rusty / jam","RemarkList":["NO OIL","Jam","step","Rusty","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":2,"Remarks":"NO OIL","Id":15,"IsActive":false},{"Description":"Fitment of Neutral Switch Sensor with Bracket","RemarkList":["TIE CLIP MISSING","GNS MISSING","BRACKET MISSING","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"TIE CLIP MISSING","Id":16,"IsActive":false},{"Description":"Fitment of filler plug & mark","RemarkList":["MISSING","DAMAGE","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"DAMAGE","Id":17,"IsActive":false},{"Description":"Fitment and any Speedo Sensor","RemarkList":["MISSING","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"MISSING","Id":18,"IsActive":false},{"Description":"Marking on gearbox","RemarkList":["AIR LEAK","TEST OK","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":2,"Remarks":"AIR LEAK","Id":19,"IsActive":false},{"Description":"Presence of gearbox no. punching","RemarkList":["MISSING","INCOMPLETE","OTHER"],"ModelNumber":"5930N","TSN":"5M218L00002","DetailId":0,"StatusId":1,"Remarks":"INCOMPLETE","Id":20,"IsActive":false}]
     */

    private int Phase1Status;
    private int Phase2Status;
    private List<CheckListsBean> CheckLists;

    public int getPhase1Status() {
        return Phase1Status;
    }

    public void setPhase1Status(int Phase1Status) {
        this.Phase1Status = Phase1Status;
    }

    public int getPhase2Status() {
        return Phase2Status;
    }

    public void setPhase2Status(int Phase2Status) {
        this.Phase2Status = Phase2Status;
    }

    public List<CheckListsBean> getCheckLists() {
        return CheckLists;
    }

    public void setCheckLists(List<CheckListsBean> CheckLists) {
        this.CheckLists = CheckLists;
    }

    public static class CheckListsBean {
        /**
         * Description : Completeness of Gearbox assembly as per model
         * RemarkList : ["MODEL","LHD LEVER"]
         * ModelNumber : 5930N
         * TSN : 5M218L00002
         * DetailId : 0
         * StatusId : 1
         * Remarks : MODEL
         * Id : 1
         * IsActive : false
         */

        private String Description;
        private String ModelNumber;
        private String TSN;
        private int DetailId;
        private int StatusId;
        private String Remarks;
        private int Id;
        private boolean IsActive;
        private List<String> RemarkList;

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getModelNumber() {
            return ModelNumber;
        }

        public void setModelNumber(String ModelNumber) {
            this.ModelNumber = ModelNumber;
        }

        public String getTSN() {
            return TSN;
        }

        public void setTSN(String TSN) {
            this.TSN = TSN;
        }

        public int getDetailId() {
            return DetailId;
        }

        public void setDetailId(int DetailId) {
            this.DetailId = DetailId;
        }

        public int getStatusId() {
            return StatusId;
        }

        public void setStatusId(int StatusId) {
            this.StatusId = StatusId;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
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

        public List<String> getRemarkList() {
            return RemarkList;
        }

        public void setRemarkList(List<String> RemarkList) {
            this.RemarkList = RemarkList;
        }
    }
}
