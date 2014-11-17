package pl.gildur.simplepropertieseditor.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {

    protected Map<RGB, Color> colorTable = new HashMap<RGB, Color>(10);

    public void dispose() {
        for (Color c : colorTable.values()) {
            c.dispose();
        }
    }

    public Color getColor(RGB rgb) {
        Color color = colorTable.get(rgb);
        if (color == null) {
            color = new Color(Display.getCurrent(), rgb);
            colorTable.put(rgb, color);
        }
        return color;
    }
}
