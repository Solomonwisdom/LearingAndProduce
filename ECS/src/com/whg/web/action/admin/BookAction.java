package com.whg.web.action.admin;

import com.whg.web.action.BaseAction;
import com.whg.web.dao.BookDAO;
import com.whg.web.dao.CategoryDAO;
import com.whg.web.dao.ProductDAO;
import com.whg.web.entity.Book;
import com.whg.web.entity.Product;
import com.whg.web.impl.JdbcBookDAO;
import com.whg.web.impl.JdbcCategoryDAO;
import com.whg.web.impl.JdbcProductDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * Created by wanghaogang on 2017/6/26.
 */
public class BookAction extends BaseAction {

    private Product product;
    private Book book;
    private int id;
    private String wordNumber;
    private String category;
    private File file1;
    private String file1FileName;
    public String add() throws Exception {
        String[] categories = category.split(",");
        if(product == null)
            product = new Product();
        product.setDangPrice(book.getDangPrice());
        product.setFixedPrice(book.getFixedPrice());
        product.setDescription(book.getDescription());
        product.setProductName(book.getProductName());
        book.setWordNumber(wordNumber + "万");
        book.setCatalogue(book.getDescription());
        book.setPublishTime(System.currentTimeMillis());
        product.setAddTime(System.currentTimeMillis());
        product.setHasDeleted(0);
        product.setKeywords("key");
        String basePath =
                ServletActionContext.getServletContext().getRealPath("/");
        if(!basePath.endsWith("/"))
            basePath += File.separator;
        basePath += "productImages";
        if(new File(basePath).isDirectory() == false) {
            new File(basePath).mkdir();
        }
        if(file1 != null) {
            product.setProductPic(file1FileName);
            book.setProductPic(file1FileName);
            ProductDAO productDAO = new JdbcProductDAO();
            int id = productDAO.save(product);
            BookDAO bookDAO = new JdbcBookDAO();
            book.setId(id);
            bookDAO.save(book);

            FileUtils.copyFile(file1, new File(basePath+File.separator+file1FileName));
            CategoryDAO categoryDAO = new JdbcCategoryDAO();
            for(String category: categories) {
                categoryDAO.save(id, Integer.parseInt(category));
            }
        }
        return "success";
    }

    public String delete() throws Exception {
        new JdbcBookDAO().delete(id);
        new JdbcProductDAO().delete(id);
        new JdbcCategoryDAO().delete(id);
        return "success";
    }

    public String update() throws Exception {
        String[] categories = category.split(",");
        if(product == null)
            product = new Product();
        product.setId(book.getId());
        product.setDangPrice(book.getDangPrice());
        product.setFixedPrice(book.getFixedPrice());
        product.setDescription(book.getDescription());
        product.setProductName(book.getProductName());
        book.setWordNumber(wordNumber + "万");
        book.setCatalogue(book.getDescription());
        book.setPublishTime(System.currentTimeMillis());
        product.setAddTime(System.currentTimeMillis());
        product.setHasDeleted(0);
        product.setKeywords("key");
        product.setId(book.getId());
        String basePath =
                ServletActionContext.getServletContext().getRealPath("/");
        if(!basePath.endsWith("/"))
            basePath += File.separator;
        basePath += "productImages";
        if(new File(basePath).isDirectory() == false) {
            new File(basePath).mkdir();
        }
        if(file1 != null) {
            product.setProductPic(file1FileName);
            book.setProductPic(file1FileName);
            ProductDAO productDAO = new JdbcProductDAO();
            int id = book.getId();
            BookDAO bookDAO = new JdbcBookDAO();
            bookDAO.update(book);
            productDAO.update(product);
            FileUtils.copyFile(file1, new File(basePath+File.separator+file1FileName));
            CategoryDAO categoryDAO = new JdbcCategoryDAO();
            categoryDAO.delete(product.getId());
            for(String category: categories) {
                categoryDAO.save(id, Integer.parseInt(category));
            }
        }
        return "success";
    }

    public String detail() throws Exception {
        BookDAO bookDAO = new JdbcBookDAO();
        CategoryDAO categoryDAO = new JdbcCategoryDAO();
        book = bookDAO.findById(id);
        wordNumber = book.getWordNumber();
        System.out.println(wordNumber);
        wordNumber = wordNumber.substring(0, wordNumber.indexOf("万"));
        System.out.println(wordNumber);
        category = categoryDAO.queryCat(book.getId());
        return "success";
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public File getFile1() { return file1; }

    public void setFile1(File file1) { this.file1 = file1;}

    public String getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(String wordNumber) {
        this.wordNumber = wordNumber;
    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
    public String getFile1FileName() {
        return file1FileName;
    }

    public void setFile1FileName(String file1FileName) {
        this.file1FileName = file1FileName;
    }

}
