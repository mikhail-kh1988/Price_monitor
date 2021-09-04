package com.pricemonitor.tools;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XLSXFileReader {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private java.util.List<Product> productList;
    private java.util.List<Merchant> merchantList;

    public XLSXFileReader(InputStream file) throws IOException {
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet("product");
        productList = new ArrayList<>();
        merchantList = new ArrayList<>();
        this.getListDataFromFile();
    }

    public java.util.List<Product> getProductListFromFile(){
        productList.remove(0);
        return productList;
    }

    public java.util.List<Merchant> getMerchantListFromFile(){
        merchantList.remove(0);
        return merchantList;
    }

    public void getListDataFromFile(){
        for (Sheet sheet: workbook) {
            for (Row row: sheet) {
                productList.add(this.getProductFromRow(row));
                merchantList.add(this.getMerchantFromRow(row));
            }
        }
    }

    private Product getProductFromRow(Row row){
        Product product = new Product();
        Price price = new Price();
        Category category = new Category();
        price.setMoney("RUB");
        for (Cell cell: row) {
            if(row.getRowNum() > 0){
                switch (cell.getColumnIndex()) {
                    case 0 : product.setName(cell.getStringCellValue());
                    break;
                    case 1 : product.setBoxing(cell.getStringCellValue());
                    break;
                    case 2 : category.setName(cell.getStringCellValue());
                    break;
                    case 3 : price.setTotal((int) cell.getNumericCellValue());
                    break;
                }
            }
        }
        product.setCategory(category);
        java.util.List<Price> priceList = new ArrayList<>();
        priceList.add(price);
        product.setPriceList(priceList);
        return product;
    }

    private Merchant getMerchantFromRow(Row row){
        Merchant merchant = new Merchant();
        for (Cell cell: row){
            if (row.getRowNum() > 0){
                switch (cell.getColumnIndex()){
                    case 4: merchant.setName(cell.getStringCellValue());
                    break;
                    case 5: merchant.setAddress(cell.getStringCellValue());
                    break;
                }
            }
        }
        return merchant;
    }
}
