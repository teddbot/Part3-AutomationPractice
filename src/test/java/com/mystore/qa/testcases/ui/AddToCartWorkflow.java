package com.mystore.qa.testcases.ui;

import com.mystore.qa.pages.*;
import com.mystore.qa.util.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddToCartWorkflow {
    private WebDriver driver;
    private Actions action;

    private Clothes clothes;
    private Cart cart;
    private ShoppingActions shoppingActions;
    private CartSummary summary;
//    private SignInForm signinForm;
    private Account account;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();

        action = new Actions(driver);

        clothes = new Clothes(driver);
        cart = new Cart(driver);
        shoppingActions = new ShoppingActions(driver);
//        signinForm = new SignInForm(driver);
        summary = new CartSummary(driver);
        account = new Account(driver);

        String baseUrl = "http://automationpractice.com/index.php";
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterClass
    public void closeAll() {
        account.getAccountLogout().click();
        driver.quit();
    }

    @Test(priority = 1)
    public void selectChlotes() {
        Assert.assertTrue(clothes.getDressesBtn().isDisplayed());

        action.moveToElement(clothes.getDressesBtn()).perform();
        
        Assert.assertTrue(clothes.getCasualDressesBtn().isDisplayed());

        action.moveToElement(clothes.getCasualDressesBtn()).perform();
        clothes.getCasualDressesBtn().click();

//        Assert.assertTrue(clothes.getSummerDressProduct(1).isDisplayed());
//        Assert.assertTrue(clothes.getSummerDressProduct(2).isDisplayed());
//        Assert.assertTrue(clothes.getSummerDressProduct(3).isDisplayed());
//        Assert.assertEquals(clothes.getDressesCount().size(), 3);

        action.moveToElement(clothes.getCasualDressProduct(1)).perform();
        action.moveToElement(shoppingActions.getAddToCartBtn()).perform();

        Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());

        action.click(shoppingActions.getAddToCartBtn()).build().perform();
        action.click(shoppingActions.getContinueShopingBtn()).build().perform();

        Assert.assertTrue(shoppingActions.getContinueShopingBtn().isDisplayed());

        action.moveToElement(cart.getCartTab()).perform();

        Assert.assertEquals(cart.getCartProductsQty().size(), 1);

        // 2. dress
        action.moveToElement(clothes.getDressesBtn()).perform();

        Assert.assertTrue(clothes.getCasualDressesBtn().isDisplayed());

        action.moveToElement(clothes.getCasualDressesBtn()).perform();
        clothes.getCasualDressesBtn().click();
        action.moveToElement(clothes.getCasualDressProduct(1)).perform();
        action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
        action.click(shoppingActions.getAddToCartBtn()).build().perform();

        Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());

        action.click(shoppingActions.getContinueShopingBtn()).build().perform();
        action.moveToElement(cart.getCartTab()).perform();

        Assert.assertEquals(cart.getCartProductsQty().size(), 1);

  

//    @Test(priority = 4)
//    public void checkingCartProductsQtyAndPrice() {
//        Assert.assertEquals(cart.getCartProductsQty().size(), 2);
//
//        action.moveToElement(clothes.getDressesBtn()).perform();
//        action.moveToElement(clothes.getEveningDressesBtn()).perform();
//        action.moveToElement(clothes.getEveningDressProduct(1)).perform();
//        action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
//        action.click(shoppingActions.getAddToCartBtn()).build().perform();
//        action.click(shoppingActions.getContinueShopingBtn()).build().perform();
//
//        action.moveToElement(cart.getCartTab()).perform();
//        action.moveToElement(cart.getCartProductsQty(1)).perform();
//
//        Assert.assertEquals(cart.getCartProductsQty(1).getText(), "1");
//
//        action.moveToElement(cart.getCartProductsQty(2)).perform();
//
//        Assert.assertEquals(cart.getCartProductsQty(2).getText(), "2");
//
//        action.moveToElement(cart.getCartShipingCost()).perform();
//
//        Assert.assertEquals(cart.getCartShipingCost().getText(), "$2.00");
//
//        action.moveToElement(cart.getCartTotalPrice()).perform();
//
//        Assert.assertEquals(cart.getCartTotalPrice().getText(), "$132.96");
    }

    @Test(priority = 5)
    public void continueToShoppingSummary() {
        action.moveToElement(cart.getCartTab()).perform();
        action.moveToElement(cart.getCartTabCheckOutBtn()).perform();

        Assert.assertTrue(cart.getCartTabCheckOutBtn().isDisplayed());

        action.click(cart.getCartTabCheckOutBtn()).build().perform();
        ;

        Assert.assertTrue(summary.getCartSummaryTable().isDisplayed());
        Assert.assertEquals(summary.getCartSummTotalProductsNum().size(), 1);
        Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$26.00");
        Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$26.00");
        Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
        Assert.assertTrue(summary.getCartSummQtyPlus(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyPlus(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyMinus(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyMinus(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyInput(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyInput(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyPlus(2).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyPlus(2).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyMinus(2).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyMinus(2).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyInput(2).isDisplayed());
        Assert.assertTrue(summary.getCartSummQtyInput(2).isDisplayed());
        Assert.assertTrue(summary.getCartSummDeleteBtn(1).isDisplayed());
        Assert.assertTrue(summary.getCartSummDeleteBtn(2).isDisplayed());
    }

//    @Test(priority = 6)
//    public void increaseQtyOfProduct1() {
//        Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$130.96");
//        Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$132.96");
//        Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
//
//        summary.getCartSummQtyPlus(1).click();
//        driver.navigate().refresh();
//
//        Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$159.94");
//        Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$161.94");
//        Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
//    }

}
