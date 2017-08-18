package com.whg.web.action.main;

import java.util.List;

import com.whg.web.dao.BookDAO;
import com.whg.web.dao.CategoryDAO;
import com.whg.web.entity.Book;
import com.whg.web.entity.Category;
import com.whg.web.impl.JdbcBookDAO;
import com.whg.web.impl.JdbcCategoryDAO;

public class BookListAction {
	private int pid;
	private int id;
	private List<Category> cats;
	private List<Book> books;
	private int totalNum=0;
	private int size=3;
	private int page=1;
	private int max=0;
	private String select_order="up";

	public String execute() throws Exception{
		CategoryDAO dao=new JdbcCategoryDAO();
		cats=dao.findByParentId(pid);
		for(Category c:cats){
			totalNum+=c.getPnum();
		}

		BookDAO bdao =new JdbcBookDAO();
//		if(select_order.equals("up")){
//			books=bdao.findUpByCatId(id,size,page);
//		}else{
//			books=bdao.findDownByCatId(id,size,page);
//		}
//		int count=bdao.count(id);
//		if(count%size==0){
//			max=count/size;
//		}else{
//			max=count/size+1;
//		}
		books = bdao.findUpByCatId(id);
		return "success";
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public List<Category> getCats() {
		return cats;
	}
	public void setCats(List<Category> cats) {
		this.cats = cats;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getSelect_order() {
		return select_order;
	}
	public void setSelect_order(String select_order) {
		this.select_order = select_order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
