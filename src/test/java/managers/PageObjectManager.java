package managers;

import com.microsoft.playwright.Page;
import cucumber.ScenarioContext;
import cucumber.TestContext;
import pageObjects.GooglePage;
import utils.BrowserUtils;

public class PageObjectManager {
    protected BrowserUtils browserUtils;
    private GooglePage googlePage;
    protected Page page;
    private TestContext testContext;

    public PageObjectManager(Page page) {

        this.page = page;

    }



    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(page) : googlePage;
    }

    public BrowserUtils getBrowserUtils(){return (browserUtils == null) ? browserUtils = new BrowserUtils(page) : browserUtils;}


}
