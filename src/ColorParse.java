
import java.awt.*;

public class ColorParse {
    public static Color StringToColor (String str) {
        try {
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return EligibleColor(str);
    }

    public static Color EligibleColor (String str) {
        String[] colors = str.split(",");
        int r = Integer.parseInt(colors[0].trim());
        int g = Integer.parseInt(colors[1].trim());
        int b = Integer.parseInt(colors[2].trim());
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("This color scheme does not exist!!");
        }


        return new Color(r,g,b);
    }
}
