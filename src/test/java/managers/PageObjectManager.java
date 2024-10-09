package managers;

import com.microsoft.playwright.Page;
import pageObjects.GooglePage;

public class PageObjectManager {

    protected Page page;

    public PageObjectManager(Page page) {
        this.page = page;
    }

    private GooglePage googlePage;

    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(page) : googlePage;
    }
}
