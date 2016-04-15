package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

public abstract class BasePage {
    protected static String url = "http://localhost:8080/";
    protected static String adminUrl = "http://localhost:8080/admin/";

    public void storeStringToScenarioDataStore(String key, String value) {
        DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
        scenarioStore.put(key, value);
    }

    public String fetchStringFromScenarioDataStore(String key) {
        DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
        return (String) scenarioStore.get(key);
    }
}
