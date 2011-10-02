/**
 * 
 */
package com.jchatting.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jchatting.client.config.ClientConfig;
import com.jchatting.server.config.ServerConfig;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:51:27
 */
public class ConfigXmlParse {

	public final static String SERVER_CONFIG_FILE = "res/serverConfig.xml";
	public final static String CLIENT_CONFIG_FLE = "res/clientConfig.xml";
	
	public static ClientConfig parseClientXml() {
		ClientConfig config = new ClientConfig();
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(CLIENT_CONFIG_FLE));
			
			Element root = document.getRootElement();
			Element ipElement = root.element("ip");
			config.setIp(ipElement.getText());
			Element portElement = root.element("port");
			try {
				config.setPort(Integer.valueOf(portElement.getText()));
			} catch (NumberFormatException e) {
				config.setPort(1234);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件未找到！");
			config.setIp("127.0.0.1");
			config.setPort(1234);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件解析发生错误！");
			config.setIp("127.0.0.1");
			config.setPort(1234);
		}
		return config;
	}
	
	public static ServerConfig parseServerXml() {
		ServerConfig config = new ServerConfig();
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(SERVER_CONFIG_FILE));
			
			Element root = document.getRootElement();
			Element portElement = root.element("port");
			config.setPort(Integer.valueOf(portElement.getText()));
			Element timeElement = root.element("time");
			config.setOfflineTime(Integer.valueOf(timeElement.getText()));
			Element maxThreadElement = root.element("maxThread");
			config.setMaxThread(Integer.valueOf(maxThreadElement.getText()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件未找到！");
			config.setPort(1234);
			config.setOfflineTime(60);
			config.setMaxThread(100);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件解析发生错误！");
			config.setPort(1234);
			config.setOfflineTime(60);
			config.setMaxThread(100);
		} catch (NumberFormatException e) {
			System.out.println("dbconfig.xml解析中数据格式转换错误！");
			config.setPort(1234);
			config.setOfflineTime(60);
			config.setMaxThread(100);
		}
		return config;
	}

}
