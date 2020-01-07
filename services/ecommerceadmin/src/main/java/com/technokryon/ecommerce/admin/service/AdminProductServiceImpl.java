package com.technokryon.ecommerce.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.technokryon.ecommerce.admin.dao.AdminProductDao;
import com.technokryon.ecommerce.admin.pojo.Product;

@Service("AdminProductService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdminProductServiceImpl implements AdminProductService {

	@Autowired
	private AdminProductDao O_AdminProductDao;

	@Override
	public String addProduct(Product O_Product) {
		// TODO Auto-generated method stub
		return O_AdminProductDao.addProduct(O_Product);
	}

	@Override
	public Boolean checkSkuAvailable(String sku) {
		// TODO Auto-generated method stub
		return O_AdminProductDao.checkSkuAvailable(sku);
	}

}
