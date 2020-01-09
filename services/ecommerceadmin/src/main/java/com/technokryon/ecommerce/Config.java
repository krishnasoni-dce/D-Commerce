package com.technokryon.ecommerce;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.technokryon.ecommerce.admin.model.TKECMATTRIBUTE;
import com.technokryon.ecommerce.admin.model.TKECMCATEGORY;
import com.technokryon.ecommerce.admin.model.TKECMIMAGE;
import com.technokryon.ecommerce.admin.model.TKECMPRODUCT;
import com.technokryon.ecommerce.admin.model.TKECMPRODUCTTYPE;
import com.technokryon.ecommerce.admin.model.TKECMROLE;
import com.technokryon.ecommerce.admin.model.TKECMUSER;
import com.technokryon.ecommerce.admin.model.TKECTOPTIONATTRIBUTE;
import com.technokryon.ecommerce.admin.model.TKECTPRODUCTATTRIBUTE;
import com.technokryon.ecommerce.admin.model.TKECTUSERAPPLYROLE;
import com.technokryon.ecommerce.admin.model.TKECTUSERAUDIT;
import com.technokryon.ecommerce.admin.model.TKECTUSERSESSION;



@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@Configuration
@PropertySource("classpath:db.properties")
@PropertySource("classpath:mail.properties")
@EnableTransactionManagement
public class Config {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getECommerceDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean getECommerceSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getECommerceDataSource());

		Properties props = new Properties();
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

		factoryBean.setHibernateProperties(props);
		// factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedClasses(TKECMUSER.class, TKECTUSERSESSION.class, TKECTUSERAUDIT.class,
				TKECMCATEGORY.class, TKECMATTRIBUTE.class, TKECTOPTIONATTRIBUTE.class, TKECTPRODUCTATTRIBUTE.class,
				TKECMIMAGE.class, TKECMPRODUCT.class, TKECMPRODUCTTYPE.class,
				TKECMROLE.class, TKECTUSERAPPLYROLE.class);

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getECommerceTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getECommerceSessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public ModelMapper getModelMapper() {

		ModelMapper O_ModelMapper = new ModelMapper();

		return O_ModelMapper;
	}

}