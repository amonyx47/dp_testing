import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;

public class GraphicalTestingUtils {

    private static double luminanace(int r, int g, int b) {
        int result = (299*r + 587*g + 114*b) / 1000;
        if(result == 0){
            return 1;
        }
        return result;
    }

    public static double contrast(Color col1, Color col2){
        return luminanace(col1.getColor().getRed(), col1.getColor().getGreen(), col1.getColor().getBlue()) / luminanace(col2.getColor().getRed(), col2.getColor().getGreen(), col2.getColor().getBlue());
    }

    public static String findParentElementBackgroundColorFromListWithDisplayVisile(WebDriver driver, ArrayList<WebElement> navigationItems){
        String parentMenuElementBackgroundColor = "";

        for(WebElement element : navigationItems){
            if(isNavigationItem(element)) {
                parentMenuElementBackgroundColor = element.findElement(By.xpath("..")).getCssValue("background-color");
                break;
            }
        }

        return parentMenuElementBackgroundColor;
    }

    public static boolean isNavigationItem(WebElement element){
        return element.getText().trim().equals("") && element.findElement(By.xpath("..")).getCssValue("display").equals("none");
    }

    public static Color transformStringToColor(String col){
        String[] backgroundColorNumbers = col.replace("rgb(", "").replace(")", "").split(",");
        return new Color(Integer.valueOf(backgroundColorNumbers[0].trim()), Integer.valueOf(backgroundColorNumbers[1].trim()), Integer.valueOf(backgroundColorNumbers[2].trim()), 1);
    }

}
