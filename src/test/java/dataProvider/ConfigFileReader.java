package dataProvider;

import enums.BrowserType;
import enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "configs//Configuration.properties";


    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getProperty(String keyWord){

        return properties.getProperty(keyWord);
    }

    public long getWaitTime() {
        String waitTime = properties.getProperty("waitTime");
        if (waitTime != null) {
            return Long.parseLong(waitTime);
        } else {
            throw new RuntimeException("waitTime not specified in the Configuration.properties file.");
        }
    }

    public String getGoogleUrl() {
        String GoogleUrl = properties.getProperty("GoogleUrl");
        if(GoogleUrl != null) return GoogleUrl;
        else throw new RuntimeException("GoogleUrl not specified in the Configuration.properties file.");
    }

    public BrowserType getBrowser() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) return BrowserType.CHROME;
        else if(browserName.equals("firefox")) return BrowserType.FIREFOX;
        else if(browserName.equals("edge")) return BrowserType.EDGE;
        else if(browserName.equals("remote_chrome")) return BrowserType.REMOTECHROME;
        else if(browserName.equals("remote_firefox")) return BrowserType.REMOTEFIREFOX;
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if(environmentName.equals("remote")) return EnvironmentType.REMOTE;
        else if(environmentName.equals("browserstack")) return EnvironmentType.BROWSERSTACK;
        else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
    }

}
