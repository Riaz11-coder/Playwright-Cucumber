package managers;

import com.microsoft.playwright.Page;
import pageObjects.GooglePage;
import utilities.BrowserUtils;

public class PageObjectManager {
    protected BrowserUtils browserUtils;
    private GooglePage googlePage;
    protected Page page;

    public PageObjectManager(Page page) {
        this.page = page;
    }



    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(page) : googlePage;
    }

    public BrowserUtils getBrowserUtils(){
        return (browserUtils == null) ? browserUtils = new BrowserUtils(page) : browserUtils;
    }
}
