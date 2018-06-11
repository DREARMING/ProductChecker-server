package com.mvcoder.actions;

import com.mvcoder.bean.Checkcode;
import com.mvcoder.bean.Result;
import com.mvcoder.dao.ICheckCodeDao;
import com.mvcoder.utils.TextUtils;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Timestamp;
import java.util.List;

public class CheckCodeAction extends ActionSupport {

    private int codeId;
    private int pdType;
    private String codeStr;

    private int checkNum;
    private int userId;

    private int pageNum;
    private int pageSize;
    private String searchStr;

    private ICheckCodeDao checkDao;

    private Result result = new Result();

    public String addCheckCode(){
        if(TextUtils.isEmpty(codeStr)){
            result.setCode(-1);
            result.setMsg("codeStr 不能为空");
            return SUCCESS;
        }
        if(pdType == 0){
            result.setCode(-2);
            result.setMsg("pdType 不能为空");
            return SUCCESS;
        }
        Checkcode checkcode = new Checkcode();
        checkcode.setCodeStr(codeStr);
        checkcode.setCreatorId(userId);
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        checkcode.setCreateDate(curTime);
        checkcode.setCurCheckDate(curTime);
        checkcode.setPdType(pdType);
        Checkcode data = checkDao.addCheckCode(checkcode);
        if(data == null){
            result.setCode(-3);
            result.setMsg("不能插入相同的序列号");
        }else{
            result.setCode(200);
            result.setData(data);
        }
        return SUCCESS;
    }

    public String updateCheckCode(){
        Checkcode checkcode = checkDao.getCheckCode(codeId);
        if(checkcode == null){
            result.setCode(-1);
            result.setMsg("找不到该序列号");
            return SUCCESS;
        }
        checkcode.setCodeStr(codeStr);
        boolean flag = checkDao.updateCheckCode(checkcode);
        if(!flag){
            result.setCode(-1);
            result.setMsg("更新序列号失败，请检查是否是与其他序列号重名！");
        }else{
            result.setCode(200);
        }
        return SUCCESS;
    }

    public String delCheckCode(){
        boolean flag = checkDao.delCheckCode(codeId, userId);
        if(flag){
            result.setCode(200);
        }else {
            result.setCode(-1);
            result.setMsg("删除该序列号失败");
        }
        return SUCCESS;
    }

    public String checkCode(){
        result.setCode(200);
        result.setData("welcome to checkCode !");
        return SUCCESS;
    }

    public String getCheckCodeList(){
        List<Checkcode> list = checkDao.queryCheckCodeList(userId,pdType,pageNum,pageSize,searchStr);
        if(list != null && list.size() > 0) {
            result.setCode(200);
            result.setData(list);
        }else{
            result.setCode(-1);
            result.setMsg("查询不到该用户的序列号信息");
        }
        return SUCCESS;
    }


    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

    public ICheckCodeDao getCheckDao() {
        return checkDao;
    }

    public void setCheckDao(ICheckCodeDao checkDao) {
        this.checkDao = checkDao;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public int getPdType() {
        return pdType;
    }

    public void setPdType(int pdType) {
        this.pdType = pdType;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
