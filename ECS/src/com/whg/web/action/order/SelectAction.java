package com.whg.web.action.order;

import java.util.List;

import com.whg.web.dao.AddressDAO;
import com.whg.web.entity.Address;
import com.whg.web.impl.JdbcAddressDAO;

public class SelectAction {
	private List<Address> adds;
	private int userId;
	private int id;

	private Address address;

	public List<Address> getAdds() {
		return adds;
	}

	public void setAdds(List<Address> adds) {
		this.adds = adds;
	}

	public String all() throws Exception {
		AddressDAO dao = new JdbcAddressDAO();
		adds = dao.findAll(userId);

		return "success";
	}

	public String findById() throws Exception {
		AddressDAO dao = new JdbcAddressDAO();
		address = dao.findById(id);

		return "success";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
