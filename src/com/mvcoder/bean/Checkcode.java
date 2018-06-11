package com.mvcoder.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Checkcode {
    private int codeId;
    private int pdType;
    private String codeStr;
    private int checkNum;
    private int creatorId;
    private Timestamp curCheckDate;
    private Timestamp createDate;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "codeId", nullable = false)
    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Basic
    @Column(name = "pdType", nullable = false)
    public int getPdType() {
        return pdType;
    }

    public void setPdType(int pdType) {
        this.pdType = pdType;
    }

    @Basic
    @Column(name = "codeStr", nullable = false, length = -1)
    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

    @Basic
    @Column(name = "checkNum", nullable = false)
    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    @Basic
    @Column(name = "creatorId", nullable = false)
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "curCheckDate", nullable = false)
    public Timestamp getCurCheckDate() {
        return curCheckDate;
    }

    public void setCurCheckDate(Timestamp curCheckDate) {
        this.curCheckDate = curCheckDate;
    }

    @Basic
    @Column(name = "createDate", nullable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkcode checkcode = (Checkcode) o;
        return codeId == checkcode.codeId &&
                pdType == checkcode.pdType &&
                checkNum == checkcode.checkNum &&
                creatorId == checkcode.creatorId &&
                Objects.equals(codeStr, checkcode.codeStr) &&
                Objects.equals(curCheckDate, checkcode.curCheckDate) &&
                Objects.equals(createDate, checkcode.createDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codeId, pdType, codeStr, checkNum, creatorId, curCheckDate, createDate);
    }
}
