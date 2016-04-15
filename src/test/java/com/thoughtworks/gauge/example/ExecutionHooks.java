package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.example.pages.ProductListPage;

public class ExecutionHooks {

    @BeforeScenario(tags = {"edit"})
    public void findAndStoreProductId() {
        ProductListPage listPage = new ProductListPage();
        listPage.navigate();
        listPage.search("The Way to Go");
        listPage.openFirstProduct();
        listPage.storeStringToScenarioDataStore("productId", listPage.getProductId());
    }
}
