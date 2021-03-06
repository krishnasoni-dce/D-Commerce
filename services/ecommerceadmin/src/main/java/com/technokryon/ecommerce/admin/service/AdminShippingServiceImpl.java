package com.technokryon.ecommerce.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.technokryon.ecommerce.admin.dao.AdminShippingDao;
import com.technokryon.ecommerce.admin.pojo.ShippingCost;

@Service("AdminShippingService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

public class AdminShippingServiceImpl implements AdminShippingService {

	@Autowired
	private AdminShippingDao adminShippingDao;

	@Override
	public void addShippingCost(ShippingCost shippingCost) {
		adminShippingDao.addShippingCost(shippingCost); 
	}

}
