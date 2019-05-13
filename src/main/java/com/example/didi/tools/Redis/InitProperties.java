package com.example.didi.tools.Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Initialization Properties
 * 配置文件加载
 */
public class InitProperties {
    public static final String PFILEPATH = File.separatorChar + "resources" + File.separatorChar + "config"
            + File.separatorChar + "config.properties";

    private Logger logger = LoggerFactory.getLogger(InitProperties.class);

    private Properties config = new Properties();

    public static Map<String, String> mapproperties = new HashMap<String, String>();


    public InitProperties() {
        // 构造初始配置文件
        init();

    }

    /**
     * 从配置文件中获取登录所需参数，如果用户有配置则优先使用用户配置
     */
    private void init() {
        logger.info("加载配置"+ PFILEPATH);
        StringBuilder content = new StringBuilder();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/config/config.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0 && !line.startsWith("#")) {
                    if (line.contains("=") && line.split("=").length == 2) {
                        String key = line.split("=")[0].trim();
                        String value = line.split("=")[1].trim();
                        if (!System.getProperties().containsKey(key.toString()) && !value.isEmpty()) {
                            InitProperties.mapproperties.put(key, value);
                            System.setProperty(key, value);
                        }
                    }
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
