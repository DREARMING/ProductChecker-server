package com.mvcoder.dao;

import com.mvcoder.bean.Checkcode;

import java.util.List;

public interface ICheckCodeDao {

    Checkcode addCheckCode(Checkcode checkcode);

    boolean updateCheckCode(Checkcode checkcode);

    boolean delCheckCode(int codeId, int userId);

    List<Checkcode> queryCheckCodeList(int creatorId);

    List<Checkcode> queryCheckCodeList(int creatorId, int pdType, int pageNum, int pageSize, String searchStr);

    Checkcode getCheckCode(String codeStr);

    Checkcode getCheckCode(int codeId);
}
