package qaDemoPom;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QADemoPoductCatalog {

    WebDriver driver;
    WebDriverWait wait;

   
    public QADemoPoductCatalog(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//button[@data-testid='browse-products-button']")
    private WebElement productLink;
    
    @FindBy(xpath = "//h1[normalize-space()='Product Catalog']")
    private WebElement productCatalogHeading;

    @FindBy(xpath = "//a[@data-testid='navbar-cart-link']")
    private WebElement cartIcon;
    
//    @FindBy(xpath="//p[@data-testid='product-description-4']")
//    private WebElement product;
    
    @FindBy(xpath = "//div[starts-with(@data-testid,'product-card-')]")
    private List<WebElement> productCards;
    
    @FindBy(xpath="//button[@data-testid='product-add-to-cart-button']")
    private WebElement addToCart;
    
    @FindBy(xpath="//button[@data-testid='product-add-to-cart-3']")
    private WebElement addbutton;
    
    


	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getProductCatalogHeading() {
		return productCatalogHeading;
	}

	public WebElement getCartIcon() {
		return cartIcon;
	}

//	public WebElement getProduct() {
//		return wait.until(ExpectedConditions.elementToBeClickable(product));
//	}

	public WebElement getAddToCart() {
		return wait.until(ExpectedConditions.elementToBeClickable(addToCart));
	}
	
	public WebElement getAddbutton() {
			return wait.until(ExpectedConditions.elementToBeClickable(addbutton));
		}
	
	 public void clickProduct(int productNumber) {

	        String expectedId = "product-card-" + productNumber;

	        for (WebElement card : productCards) {

	            if (expectedId.equals(card.getAttribute("data-testid"))) {

	                ((JavascriptExecutor) driver).executeScript(
	                    "arguments[0].scrollIntoView({block:'center'});",
	                    card
	                );

	                wait.until(ExpectedConditions.elementToBeClickable(card)).click();

	                return;
	            }
	        }

	
	 }   
}