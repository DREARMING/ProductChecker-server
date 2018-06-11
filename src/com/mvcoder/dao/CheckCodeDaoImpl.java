package com.mvcoder.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvcoder.bean.Checkcode;
import com.mvcoder.utils.TextUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

public class CheckCodeDaoImpl  implements ICheckCodeDao{

    private BaseHibernateDao baseDao;

    public BaseHibernateDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseHibernateDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public Checkcode addCheckCode(Checkcode checkcode) {
        Session session = baseDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Checkcode where creatorId=? and pdType=? and codeStr=?";
            Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
            query.setParameter(0, checkcode.getCreatorId());
            query.setParameter(1, checkcode.getPdType());
            query.setParameter(2, checkcode.getCodeStr());
            Checkcode result = query.uniqueResult();
            if(result != null) return null;
            session.save(checkcode);
            transaction.commit();
            return checkcode;
        }catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateCheckCode(Checkcode checkcode) {
        Session session = baseDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Checkcode where creatorId=? and codeId = ?";
            Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
            query.setParameter(0, checkcode.getCreatorId());
            query.setParameter(1, checkcode.getCodeId());
            Checkcode result = query.uniqueResult();
            if(result == null) return false;
            boolean flag = true;
            if(checkcode.getCheckNum() != result.getCheckNum()){
                result.setCheckNum(checkcode.getCheckNum());
                result.setCurCheckDate(new Timestamp(System.currentTimeMillis()));
            }else if(!TextUtils.isEmpty(checkcode.getCodeStr())){
                if(!checkcode.getCodeStr().equals(result.getCodeStr())){
                    result.setCheckNum(0);
                }
                result.setCodeStr(checkcode.getCodeStr());
            }else{
                flag = false;
            }
            transaction.commit();
            return flag;
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delCheckCode(int codeId, int userId) {
        Session session = baseDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Checkcode where codeId=? and creatorId=?";
            Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
            query.setParameter(0, codeId);
            query.setParameter(1, userId);
            Checkcode result = query.uniqueResult();
            if(result == null) return false;
            session.delete(result);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Checkcode> queryCheckCodeList(int creatorId) {
        Session session = baseDao.getSession();
        String hql = "from Checkcode where creatorId=? order by codeId desc";
        Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
        query.setParameter(0, creatorId);
        List<Checkcode> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Checkcode> queryCheckCodeList(int creatorId, int pdType, int pageNum, int pageSize, String searchStr) {
        Session session = baseDao.getSession();
        String hql = null;
        if(!TextUtils.isEmpty(searchStr)){
            if(pdType == 0) {
                hql = "from Checkcode where creatorId=? and codeStr like ? order by codeId desc";
            }else{
                hql = "from Checkcode where creatorId=? and codeStr like ? and pdType=? order by codeId desc";
            }
        }else {
            if(pdType == 0) {
                hql = "from Checkcode where creatorId=? order by codeId desc";
            }else{
                hql = "from Checkcode where creatorId=? and pdType=? order by codeId desc";
            }
        }
        Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
        query.setParameter(0, creatorId);
        if(!TextUtils.isEmpty(searchStr)){
            query.setParameter(1, "%" + searchStr + "%");
            if(pdType > 0){
                query.setParameter(2, pdType);
            }
        }else{
            if(pdType > 0){
                query.setParameter(1,pdType);
            }
        }
        if(pageNum > 0){
            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        List<Checkcode> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Checkcode getCheckCode(String codeStr) {
        Session session = baseDao.getSession();
        String hql = "from Checkcode where codeStr=?";
        Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
        query.setParameter(0, codeStr);
        Checkcode result = query.uniqueResult();
        session.close();
        return result;
    }

    @Override
    public Checkcode getCheckCode(int codeId) {
        Session session = baseDao.getSession();
        String hql = "from Checkcode where codeId=?";
        Query<Checkcode> query = session.createQuery(hql,Checkcode.class);
        query.setParameter(0, codeId);
        Checkcode result = query.uniqueResult();
        session.close();
        return result;
    }

    public static void main(String[] args){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        BaseHibernateDao baseHibernateDao = ac.getBean(BaseHibernateDao.class);
        CheckCodeDaoImpl test = new CheckCodeDaoImpl();
        test.setBaseDao(baseHibernateDao);

        //test.testAddCheckcode();

       // test.testUpdateCheckcode();
        test.testQueryList();
    }

    private void testAddCheckcode(){
        Checkcode checkcode = new Checkcode();
        checkcode.setCodeStr("1122334411");
        checkcode.setCheckNum(0);
        checkcode.setCreatorId(1);
        checkcode.setPdType(3);
        checkcode.setCurCheckDate(new Timestamp(System.currentTimeMillis()));
        checkcode.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Checkcode result = addCheckCode(checkcode);
        if(result !=null){
            System.out.println("插入成功： " + result.getCodeId());
        }else{
            System.out.println("插入失败");
        }
    }

    private void testUpdateCheckcode(){
        Checkcode temp = new Checkcode();
        temp.setCodeId(5);
        Checkcode checkcode = getCheckCode(temp.getCodeId());
        if(checkcode != null) {
            checkcode.setCodeStr("1122334411");
            checkcode.setCheckNum(checkcode.getCheckNum() + 1);
            boolean result = updateCheckCode(checkcode);
            if (result) {
                System.out.println("更新成功： ");
            } else {
                System.out.println("更新失败");
            }
        }else{
            System.out.println("不存在该checkcode");
        }
    }

    private void delCheckCode(){
        boolean flag = delCheckCode(3, 1);
        if(flag) System.out.println("删除成功");
    }

    private void testCheckcode(){
        Checkcode checkcode = getCheckCode("1122334411");
        if(checkcode != null){
            System.out.println("查询成功： " + checkcode.getCodeId());
        }
    }

    private void testQueryList(){
        List<Checkcode> list = queryCheckCodeList(1,3,0,0, "");
        if(list != null){
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().create();
            System.out.println(gson.toJson(list));
        }else{
            System.out.println("没有相关序列号信息");
        }
    }
}
