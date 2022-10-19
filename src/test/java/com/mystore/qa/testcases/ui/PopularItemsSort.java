package com.mystore.qa.testcases.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PopularItemsSort {
    static HashMap< BigDecimal,String> map_final_products = new HashMap<>();
    public static void sortbykey()
    {
        // TreeMap to store values of HashMap
        TreeMap<BigDecimal, String> sortedMap_final_products = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sortedMap_final_products.putAll(map_final_products);

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<BigDecimal, String> entry : sortedMap_final_products.entrySet())
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }

    @Test
    public void TestPopularItemSort() {

        Reporter.log("Testing Popular Items Sort", true);
        String url = "http://automationpractice.com/index.php";
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get(url);
        Reporter.log("Chrome Invoked and navigated to URL: " + url + "<br>", true);


        List<WebElement> list_of_popular_items = driver.findElements(By.xpath("//ul[@id='homefeatured']//a[@class='product-name']"));


        List<WebElement> list_of_popular_items_price = driver.findElements(By.xpath("//*[@id='homefeatured']/li/div/div[2]/div[1]/span[@class='price product-price']"));

        String product_name;
        String product_price;


        for(int i=0;i<list_of_popular_items.size();i++) {
            product_name = list_of_popular_items.get(i).getText();//Iterate and fetch product name
            product_price = list_of_popular_items_price.get(i).getText();//Iterate and fetch product price


            product_price = product_price.replaceAll("[^.0-9]", "");//Replace anything wil space other than numbers
//			int_product_price = Float.parseFloat(product_price);//Convert to Integer
            BigDecimal int_product_price = new BigDecimal(product_price);
            map_final_products.put(int_product_price,product_name);//Add product and price in HashMap
        }
        // Calling the function to sort by keys
        sortbykey();

    }
}
