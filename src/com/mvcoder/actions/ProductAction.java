package com.mvcoder.actions;

import com.mvcoder.bean.Product;
import com.mvcoder.bean.Result;
import com.mvcoder.dao.ProductDaoImpl;
import com.mvcoder.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.List;

public class ProductAction extends ActionSupport {


    /**
     * query
     */
    private int pageSize;
    private int pageNum;
    private String filterString;

    private int pdType;

    private String pdName;
    private String pdDescribe;

    private File uploadImg;
    private String uploadImgFileName;
    private String uploadImageContentType;

    private int userId;
    private String username;

    private ProductDaoImpl productDao;

    private Result result = new Result();

    private void init() {
        ActionContext actionContext = ActionContext.getContext();
        Integer userId = (Integer) actionContext.getSession().getOrDefault("userId", -1);
        String username = (String) actionContext.getSession().get("username");
        if (userId == -1)
            throw new IllegalStateException("用户未登录");
        this.userId = userId;
        this.username = username;
    }

    public String addProduct() {
        String filePath = saveImgFile();
        if(filePath == null){
            result.setCode(-2);
            result.setMsg("存储图片失败");
        }else{
            Product product = new Product();
            product.setCreatorId(userId);
            product.setPdName(pdName);
            product.setPdDescribe(pdDescribe);
            product.setPdImgUrl(filePath);
            Product temp = productDao.addProduct(product);
            if (temp != null) {
                result.setCode(200);
                result.setData(temp);
            } else {
                result.setCode(-1);
                result.setMsg("产品名称不能重复");
            }
        }
        return SUCCESS;
    }

    public String updateProduct() {
        if(pdType <= 0) {
            result.setCode(-1);
            result.setMsg("pdType不能小于0");
        }else{

        }
        return SUCCESS;
    }

    public String delProduct() {
        boolean del = productDao.delProduct(pdType);
        if (del) {
            result.setCode(200);
        } else {
            result.setCode(-1);
            result.setMsg("没有相关产品");
        }
        return SUCCESS;
    }

    public String getProductList() {
        List<Product> pdList;
        if (pageNum <= 0) {
            pdList = productDao.getProductList(userId);
        } else {
            pdList = productDao.getProductList(userId, pageSize, pageNum, null);
        }
        if (pdList == null || pdList.size() == 0) {
            result.setCode(-1);
            result.setMsg("没有相关产品信息");
        } else {
            result.setCode(200);
            result.setData(pdList);
        }
        return SUCCESS;
    }

    public String saveOrUpdatePd(){
        Product product = productDao.getProduct(pdType);
        if(product == null){
            product = new Product();
        }
        product.setPdName(pdName);
        product.setPdDescribe(pdDescribe);
        product.setCreatorId(userId);
        String imgUrl = product.getPdImgUrl();
        String filePath = null;
        boolean hasUploadFile = false;
        if(uploadImgFileName != null && uploadImgFileName.length()>0) {
            hasUploadFile = true;
            if (imgUrl == null || "".equals(imgUrl)) {
                filePath = saveImgFile();
            } else {
                String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                if(!fileName.equals(uploadImgFileName)){
                    String root = ServletActionContext.getServletContext().getRealPath(imgUrl.substring(imgUrl.indexOf(Constants.PROJECT_UPLOAD_DIR)));
                    boolean flag = FileUtils.deleteQuietly(new File(root));
                    if(flag){
                        System.out.println("删除原来的图片成功");
                    }
                    //同名文件不更新，即使是不同的文件。
                    filePath = saveImgFile();
                }

            }
        }
        if(hasUploadFile && filePath == null){
            result.setCode(-3);
            result.setMsg("存储图片失败");
        }else{
            product.setPdImgUrl(filePath);
            if(pdType <= 0){
                product = productDao.addProduct(product);
                if(product == null){
                    result.setCode(-1);
                    result.setMsg("添加产品失败");
                    return SUCCESS;
                }
            }else{
                boolean flag = productDao.updateProduct(product);
                if(!flag){
                    result.setCode(-2);
                    result.setMsg("更新产品失败");
                    return SUCCESS;
                }
            }
            result.setCode(200);
            result.setData(product);
        }
        return SUCCESS;
    }

    private String saveImgFile() {
        String subPath = null;
        if (username != null && !"".equals(username)) {
            subPath = username;
        } else {
            subPath = userId + "";
        }
        String rootPath = Constants.PROJECT_UPLOAD_DIR + "/" + subPath;

        String root = ServletActionContext.getServletContext().getRealPath(rootPath);

        File fileDir = new File(root);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            File imgFile = new File(root, uploadImgFileName);
            os = new FileOutputStream(imgFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            is = new FileInputStream(uploadImg);
            while (-1 != (length = is.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, length);
            }
            os.flush();
            return Constants.PROJECT_ROOT_DIR + rootPath + "/" +  uploadImgFileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null)
                    os.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPdType() {
        return pdType;
    }

    public void setPdType(int pdType) {
        this.pdType = pdType;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdDescribe() {
        return pdDescribe;
    }

    public void setPdDescribe(String pdDescribe) {
        this.pdDescribe = pdDescribe;
    }

    public File getUploadImg() {
        return uploadImg;
    }

    public void setUploadImg(File uploadImg) {
        this.uploadImg = uploadImg;
    }

    public String getUploadImgFileName() {
        return uploadImgFileName;
    }

    public void setUploadImgFileName(String uploadImgFileName) {
        this.uploadImgFileName = uploadImgFileName;
    }

    public String getUploadImageContentType() {
        return uploadImageContentType;
    }

    public void setUploadImageContentType(String uploadImageContentType) {
        this.uploadImageContentType = uploadImageContentType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProductDaoImpl getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
}
