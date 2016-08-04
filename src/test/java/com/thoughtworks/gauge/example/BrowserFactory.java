package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import net.sf.sahi.Proxy;
import net.sf.sahi.client.Browser;
import net.sf.sahi.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

public class BrowserFactory {

    public static final String LOCAL_HOST = "localhost";
    private static final String DEFAULT_BROWSER = "firefox";
    private static final int DEFAULT_PORT = 9999;
    private static Browser browser;
    private static Proxy proxy;

    @BeforeSuite
    public void setup() throws IOException {
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

    private void launchBrowser() throws IOException {
        int port = portNumber();
        System.out.println("Starting SAHI proxy on port "+ port);
        startProxy(port);
        String browserName = System.getenv("sahi.browser_name");
        int runnerId = DEFAULT_PORT - port;
        switch (browserName.toLowerCase())
        {
            case "chrome":
                browser = new Browser("open -n -a \"Google Chrome.app\"",
                        "Google Chrome",
                        "--args --new-window --incognito --user-data-dir=" + userDataDir() + "/browser/chrome/profiles/sahi" + runnerId + " --proxy-server=localhost:" + port + " --disable-popup-blocking",
                        LOCAL_HOST,
                        port);
                break;
            case "firefox":
            default:
                browser = new Browser("open -n -a \"Firefox.app\"",
                        "Firefox",
                        "--args -profile \""+ userDataDir()+"/browser/ff/profiles/sahi"+ runnerId +"\" -no-remote -new-window",
                        LOCAL_HOST,
                        port);
        }
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

    private int portNumber() throws IOException {
        for (int port = DEFAULT_PORT ; port > 9000; port--) {
            int freePort;
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                freePort= serverSocket.getLocalPort();
                serverSocket.close();
            } catch (IOException ex) {
                continue; // try next port
            }
            return freePort;
        }

        // if the program gets here, no port in the range was found
        throw new IOException("no free port found");
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
