package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import com.thoughtworks.gauge.example.pages.EditProductPage;
import com.thoughtworks.gauge.example.pages.ProductPage;
import net.sf.sahi.client.ElementStub;
import org.junit.Assert;

import java.util.List;

public class Product {
    private EditProductPage editProductPage;
    private ProductPage productPage;

    public Product() {
        this.productPage = new ProductPage();
        this.editProductPage = new EditProductPage();
    }

    @Step("Verify product author as <author>")
    public void verifyProductTitle(String author) {
        productPage.verifyAuthor(author);
    }

    @Step("Delete this product")
    public void deleteProduct() {
        productPage.deleteProduct();
    }

    @Step("Verify product <specifier> as <value>")
    public void verifyProduct(String specifier, String value) {
        verifyProductSpecifier(productPage.getElementByName(specifier), value);
    }

    @Step("Open product edit page for stored productId")
    public void openProductEditPage() {
        editProductPage.navigate();
    }

    @Step("Update product specifier to new value <table>")
    public void updateProductValue(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            openProductEditPage();
            editProductPage.updateProductValue(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)));
        }
    }

    @Step("Check product specifier has new value <table>")
    public void verifyProductValue(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            verifyProductSpecifier(productPage.getElementByName(row.getCell(columnNames.get(0))), row.getCell(columnNames.get(1)));
        }
    }

    private void verifyProductSpecifier(ElementStub element, String value) {
        Assert.assertTrue(element.getText().equals(value));
    }
}
