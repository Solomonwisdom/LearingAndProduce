package com.whg.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.entity.Address;

public interface AddressDAO {
    public void save(Address address) throws SQLException;
    public List<Address> findAll(int id) throws SQLException;
    public Address findById(int id) throws SQLException;
}
