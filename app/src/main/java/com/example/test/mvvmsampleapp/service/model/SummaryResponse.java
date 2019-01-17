package com.example.test.mvvmsampleapp.service.model;

public class SummaryResponse {


    /**
     * Phase1 : {"TotalCount":0,"NotComplianceCount":0,"ComplianceCount":0}
     * Phase2 : {"TotalCount":0,"NotComplianceCount":0,"ComplianceCount":0}
     * Phase3 : {"TotalCount":0,"NotComplianceCount":0,"ComplianceCount":0}
     */

    private Phase1Bean Phase1;
    private Phase2Bean Phase2;
    private Phase3Bean Phase3;

    public Phase1Bean getPhase1() {
        return Phase1;
    }

    public void setPhase1(Phase1Bean Phase1) {
        this.Phase1 = Phase1;
    }

    public Phase2Bean getPhase2() {
        return Phase2;
    }

    public void setPhase2(Phase2Bean Phase2) {
        this.Phase2 = Phase2;
    }

    public Phase3Bean getPhase3() {
        return Phase3;
    }

    public void setPhase3(Phase3Bean Phase3) {
        this.Phase3 = Phase3;
    }

    public static class Phase1Bean {
        /**
         * TotalCount : 0
         * NotComplianceCount : 0
         * ComplianceCount : 0
         */

        private int TotalCount;
        private int NotComplianceCount;
        private int ComplianceCount;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getNotComplianceCount() {
            return NotComplianceCount;
        }

        public void setNotComplianceCount(int NotComplianceCount) {
            this.NotComplianceCount = NotComplianceCount;
        }

        public int getComplianceCount() {
            return ComplianceCount;
        }

        public void setComplianceCount(int ComplianceCount) {
            this.ComplianceCount = ComplianceCount;
        }
    }

    public static class Phase2Bean {
        /**
         * TotalCount : 0
         * NotComplianceCount : 0
         * ComplianceCount : 0
         */

        private int TotalCount;
        private int NotComplianceCount;
        private int ComplianceCount;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getNotComplianceCount() {
            return NotComplianceCount;
        }

        public void setNotComplianceCount(int NotComplianceCount) {
            this.NotComplianceCount = NotComplianceCount;
        }

        public int getComplianceCount() {
            return ComplianceCount;
        }

        public void setComplianceCount(int ComplianceCount) {
            this.ComplianceCount = ComplianceCount;
        }
    }

    public static class Phase3Bean {
        /**
         * TotalCount : 0
         * NotComplianceCount : 0
         * ComplianceCount : 0
         */

        private int TotalCount;
        private int NotComplianceCount;
        private int ComplianceCount;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getNotComplianceCount() {
            return NotComplianceCount;
        }

        public void setNotComplianceCount(int NotComplianceCount) {
            this.NotComplianceCount = NotComplianceCount;
        }

        public int getComplianceCount() {
            return ComplianceCount;
        }

        public void setComplianceCount(int ComplianceCount) {
            this.ComplianceCount = ComplianceCount;
        }
    }
}
