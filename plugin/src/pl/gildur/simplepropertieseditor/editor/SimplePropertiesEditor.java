package pl.gildur.simplepropertieseditor.editor;

import org.eclipse.ui.editors.text.TextEditor;

public class SimplePropertiesEditor extends TextEditor {

    private ColorManager colorManager;

    public SimplePropertiesEditor() {
        super();
        colorManager = new ColorManager();
        setSourceViewerConfiguration(new PropertiesConfiguration(colorManager));
        setDocumentProvider(new PropertiesDocumentProvider());
    }

    @Override
    public void dispose() {
        colorManager.dispose();
        super.dispose();
    }
}
