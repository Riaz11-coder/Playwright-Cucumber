package utils;

import com.microsoft.playwright.Page;
import java.util.function.Consumer;

public class DialogUtil extends BasePlaywrightUtil {

    public DialogUtil(Page page) {
        super(page);
    }

    public void handleDialog(Consumer<com.microsoft.playwright.Dialog> handler) {
        page.onDialog(handler);
    }

    public void acceptNextDialog() {
        handleDialog(dialog -> dialog.accept());
    }

    public void dismissNextDialog() {
        handleDialog(dialog -> dialog.dismiss());
    }

    public void fillPromptDialog(String text) {
        handleDialog(dialog -> dialog.accept(text));
    }
}
