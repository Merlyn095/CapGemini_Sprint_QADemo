package qaDemoPom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QADemoPoductCatalog {

    WebDriver driver;
    WebDriverWait wait;

   
    public QADemoPoductCatalog(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//button[@data-testid='browse-products-button']")
    private WebElement productLink;
    
    @FindBy(xpath = "//h1[normalize-space()='Product Catalog']")
    private WebElement productCatalogHeading;

    @FindBy(xpath = "//a[@data-testid='navbar-cart-link']")
    private WebElement cartIcon;
    
    @FindBy(xpath="//p[@data-testid='product-description-4']")
    private WebElement product;
    
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

	public WebElement getProduct() {
		return product;
	}

	public WebElement getAddToCart() {
		return addToCart;
	}
	
	   public WebElement getAddbutton() {
			return addbutton;
		}
    
	
    
}