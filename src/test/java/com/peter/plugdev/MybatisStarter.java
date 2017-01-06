package com.peter.plugdev;

import com.peter.plugdev.mapper.PlugMapper;
import com.peter.plugdev.mapper.VoltageMapper;
import com.peter.plugdev.model.Voltage;
import com.peter.plugdev.util.BeansFactoryUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by miaorf on 2016/6/27.
 */
public class MybatisStarter {

    public static void main(String[] args) throws IOException {
    	ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-dao.xml"});
		//VehicleService bean=(VehicleService)ac.getBean("vehicleService");
		
    	
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        SqlSession session = sqlSessionFactory.openSession();
        try {
            //PlugMapper mapper = (PlugMapper)BeansFactoryUtil.getBeanObject("plugMapper");//(PlugMapper)ac.getBean("plugMapper");//session.getMapper(PlugMapper.class);
            //Voltage voltage = mapper.selectVoltage(1);
            //System.out.println(voltage);
            
            Voltage voltage2 = new Voltage();
            voltage2.setImei("123456789");
            voltage2.setVoltage("3.85");
            voltage2.setTime(new Timestamp(System.currentTimeMillis()));
            
            VoltageMapper voltageMapper = (VoltageMapper)BeansFactoryUtil.getBeanObject("voltageMapper");
            //voltageMapper.insertVoltage(voltage2.getImei(),voltage2.getVoltage(),voltage2.getTime());
            voltageMapper.insertVoltageEntity(voltage2);
        
        } finally {
            ;//session.close();
        }
    }

   
}