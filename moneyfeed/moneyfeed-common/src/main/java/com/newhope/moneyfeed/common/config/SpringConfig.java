//package com.newhope.zhuxiaoer.trade.common.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.support.TransactionTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//@Configuration
//public class SpringConfig {
//
//	@Autowired
//	@Qualifier("transactionManager")
//	DataSourceTransactionManager transactionManager;
//	
//	@Bean
//	TransactionTemplate transactionTemplate() {
//		TransactionTemplate transactionTemplate = new TransactionTemplate();
//		transactionTemplate.setTransactionManager(transactionManager);
//		return  transactionTemplate;
//	}
//}
