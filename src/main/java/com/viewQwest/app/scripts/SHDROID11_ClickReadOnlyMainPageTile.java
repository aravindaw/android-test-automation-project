package com.viewQwest.app.scripts;

import com.viewQwest.app.context.ClickMainPageTileContext;
import com.viewQwest.app.documents.ClickMainPageTileDocument;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class SHDROID11_ClickReadOnlyMainPageTile extends Preconditions {

    @Features("Main page activity testing")
    @Step("Click read only tile ")
    @Title("Main page tile click testing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Click a tile that is configured as read-only. Toast message should display that's saying 'Read only'..")
    @Stories({"Main page readonly tile testing"})
    @Test
    public void clickHumiditySensorIcon() throws Exception {
        ClickMainPageTileContext context = new ClickMainPageTileContext();
        context.setTileName("Sensor (relative humidity)");

        ClickMainPageTileDocument tile = new ClickMainPageTileDocument(context);
        byte[] screenshot = tile.click();
        saveScreenshot(screenshot);
    }
}
