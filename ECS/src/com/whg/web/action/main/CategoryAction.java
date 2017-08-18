package com.whg.web.action.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.CategoryDAO;
import com.whg.web.entity.Category;
import com.whg.web.impl.JdbcCategoryDAO;

public class CategoryAction {
	private List<Category> cats=new ArrayList<Category>();

	public String execute() throws Exception{
		CategoryDAO dao=new JdbcCategoryDAO();
		List<Category> all=dao.findAll();
		cats=findByParent(1,all);
		for(Category c:cats){
			List<Category> subCats=findByParent(c.getId(),all);
			c.setSubCats(subCats);
		}
		return "success";

	}private List<Category> findByParent(int parentId,List<Category> all){
		List<Category> list=new ArrayList<Category>();
		for(Category c:all){
			if(c.getParentId()==parentId){
				list.add(c);
			}

		}
		return list;

	}public List<Category> getCats() {
		return cats;
	}
	public void setCats(List<Category> cats) {
		this.cats = cats;
	}


}
   