package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import net.sf.sahi.Proxy;
import net.sf.sahi.client.Browser;
import net.sf.sahi.config.Configuration;

import java.io.File;

public class BrowserFactory {

    public static final String LOCAL_HOST = "localhost";
    private static final String DEFAULT_BROWSER = "firefox";
    private static final int DEFAULT_PORT = 9999;
    private static Browser browser;
    private static Proxy proxy;

    @BeforeSuite
    public void setup() {
        String sahiInstallDir = System.getenv("sahi.install_dir");
        if (sahiInstallDir == null || sahiInstallDir.isEmpty()) {
            throw new RuntimeException("Sahi Install directory not specified. Set in env/default/sahi_config.properties file");
        }
        Configuration.init(sahiInstallDir, userDataDir());
        launchBrowser();
    }

    @AfterSuite
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (proxy != null && proxy.isRunning()) {
            proxy.stop();
        }
    }

    private void launchBrowser() {
        int port = portNumber();
        startProxy(port);
        browser = new Browser(browserName(), LOCAL_HOST, port);
        browser.open();
    }

    private void startProxy(int port) {
        proxy = new Proxy(port);
        proxy.start(true);
        waitForProxy(10000);
    }

    public static Browser getBrowser() {
        return browser;
    }

    private int portNumber() {
        return DEFAULT_PORT;
    }

    private String browserName() {
        String browserName = System.getenv("sahi.browser_name");
        if (!(browserName == null || browserName.isEmpty())) {
            return  browserName;
        }
        return DEFAULT_BROWSER;
    }

    private String userDataDir() {
        return new File("sahi", "userdata").getAbsolutePath();
    }

    private void waitForProxy(int waitUntil) {
        int counter = 0;
        while(!proxy.isRunning() && counter < waitUntil) {
            try {
                Thread.sleep(1000);
                counter += 1000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!proxy.isRunning()) {
            throw new RuntimeException("Sahi Proxy server is not starting");
        }
    }
}
