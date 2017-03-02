package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import net.sf.sahi.Proxy;
import net.sf.sahi.client.Browser;
import net.sf.sahi.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class BrowserFactory {

    private static final String LOCAL_HOST = "localhost";
    private static ThreadLocal<Browser> browser = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<Proxy> proxy = ThreadLocal.withInitial(() -> null);

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
        if (getBrowser() != null) {
            getBrowser().close();
        }
        if (getProxy() != null && getProxy().isRunning()) {
            getProxy().stop();
        }
    }

    private void launchBrowser() throws IOException {
        int port = portNumber();
        System.out.println("Starting SAHI proxy on port " + port);
        startProxy(port);
        int runnerId = 9999 - port;
        browser.set(new Browser("open -n -a \"Google Chrome.app\"",
                "\"Google Chrome\"",
                "--args --new-window --incognito --user-data-dir=" + userDataDir() + "/browser/chrome/profiles/sahi" + runnerId + " --proxy-server=localhost:" + port + " --disable-popup-blocking",
                LOCAL_HOST,
                port));
        getBrowser().open();
    }

    private void startProxy(int port) {
        Proxy p = new Proxy(port);
        p.start(true);
        proxy.set(p);
        waitForProxy();
    }

    private Proxy getProxy() {
        return proxy.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    private int portNumber() throws IOException {
        ServerSocket s = new ServerSocket(0);
        int p = s.getLocalPort();
        s.close();
        return p;
    }

    private String userDataDir() {
        return new File("sahi", "userdata").getAbsolutePath();
    }

    private void waitForProxy() {
        int counter = 0;
        while (!getProxy().isRunning() && counter < 10000) {
            try {
                Thread.sleep(1000);
                counter += 1000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!getProxy().isRunning()) {
            throw new RuntimeException("Sahi Proxy server is not starting");
        }
    }
}
