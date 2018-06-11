package com.mvcoder.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvcoder.bean.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

public class ProductDaoImpl implements IProductDao {

    private BaseHibernateDao baseDao;

    public BaseHibernateDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseHibernateDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public Product addProduct(Product product) {
        Session session = baseDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Product where pdName=? and creatorId=?";
            Query<Product> query = session.createQuery(hql, Product.class);
            query.setParameter(0, product.getPdName());
            query.setParameter(1,product.getCreatorId());
            Product temp = query.uniqueResult();
            if (temp != null) return null;
            session.save(product);
            transaction.commit();
            return product;
        }catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delProduct(int productType) {
        Session session = baseDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Product product = session.get(Product.class, productType);
            if (product == null) return false;
            session.delete(product);
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
    public Product getProduct(int productType) {
        return (Product) baseDao.get(Product.class, productType);
    }

    @Override
    public List<Product> getProductList(int userId) {
        Session session = baseDao.getSession();
        String hql = "from Product where creatorId=? order by pdType desc";
        Query<Product> productQuery = session.createQuery(hql,Product.class);
        productQuery.setParameter(0, userId);
        List<Product> pdList = productQuery.list();
        session.close();
        return pdList;
    }

    @Override
    public List<Product> getProductList(int userId, int pageSize, int pageNum, String searchStr) {
        Session session = baseDao.getSession();
        String hql = null;
        if(searchStr != null && searchStr.length() > 0){
            hql = "from Product where creatorId=? and pdName like ? order by pdType desc";
        }else {
            hql = "from Product where creatorId=? order by pdType desc";
        }
        Query<Product> productQuery = session.createQuery(hql,Product.class);
        productQuery.setParameter(0, userId);
        if(searchStr != null && searchStr.length() > 0) {
            productQuery.setParameter(1, "%" + searchStr + "%");
        }
        if(pageNum > 0){
            productQuery.setMaxResults(pageSize);
            productQuery.setFirstResult((pageNum - 1) * pageSize);
        }
        List<Product> pdList = productQuery.list();
        session.close();
        return pdList;
    }

    @Override
    public boolean updateProduct(Product product) {
        Session session = baseDao.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Product temp = session.get(Product.class, product.getPdType());
            if (temp == null) return false;
            temp.setPdName(product.getPdName());
            temp.setPdImgUrl(product.getPdImgUrl());
            temp.setPdDescribe(product.getPdDescribe());
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

    public static void main(String[] args){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        BaseHibernateDao baseHibernateDao = ac.getBean(BaseHibernateDao.class);
        ProductDaoImpl test = new ProductDaoImpl();
        test.setBaseDao(baseHibernateDao);
        test.testAddProduct();
        //((ProductDaoImpl) test).testGetList();
        //((ProductDaoImpl) test).testDelProduct();
        //((ProductDaoImpl) test).testUpdateProduct();

    }

    private void testGetList(){
       // List<Product> productList = getProductList(1);
        List<Product> pdList = getProductList(1,3,1,"d");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().create();
        //System.out.println(gson.toJson(productList));
        System.out.println(gson.toJson(pdList));

    }

    private void testUpdateProduct(){
        Product product = new Product();
        product.setPdType(8);
        product.setPdName("设备g");
        product.setPdDescribe("该设备用于快速生成g产品 -- updateContent");
        product.setPdImgUrl("http://hello.img");
        boolean update = updateProduct(product);
        System.out.println("更新 ： " + (update?"成功":"失败"));
    }

    private void testDelProduct(){
        System.out.println("删除设备 ： " + (delProduct(2)?"成功":"失败" ));
    }

    private void testAddProduct(){
        Product product = new Product();
        product.setPdName("设备g");
        product.setCreatorId(1);
        product.setPdDescribe("该设备用于快速生成g产品");
        Product result = addProduct(product);
        if(result == null) System.out.println("插入失败");
        else
            System.out.println("插入成功 ： " + result.getPdType());
    }
}
