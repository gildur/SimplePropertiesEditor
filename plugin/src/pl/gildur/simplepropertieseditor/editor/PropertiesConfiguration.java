package pl.gildur.simplepropertieseditor.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class PropertiesConfiguration extends SourceViewerConfiguration {

    private ColorManager colorManager;

    public PropertiesConfiguration(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE, PropertiesPartitionScanner.PROPERTIES_COMMENT,
                        PropertiesPartitionScanner.PROPERTIES_ENTRY };
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

        SimpleDamagerRepaierer dr = new SimpleDamagerRepaierer(new TextAttribute(colorManager
                        .getColor(PropertiesColorConstants.ENTRY)));
        reconciler.setDamager(dr, PropertiesPartitionScanner.PROPERTIES_ENTRY);
        reconciler.setRepairer(dr, PropertiesPartitionScanner.PROPERTIES_ENTRY);

        SimpleDamagerRepaierer ndr = new SimpleDamagerRepaierer(new TextAttribute(colorManager
                        .getColor(PropertiesColorConstants.COMMENT)));
        reconciler.setDamager(ndr, PropertiesPartitionScanner.PROPERTIES_COMMENT);
        reconciler.setRepairer(ndr, PropertiesPartitionScanner.PROPERTIES_COMMENT);

        return reconciler;
    }

    private static class SimpleDamagerRepaierer extends DefaultDamagerRepairer {

        private TextAttribute textAttribute;

        public SimpleDamagerRepaierer(TextAttribute textAttribute) {
            super(new RuleBasedScanner());
            this.textAttribute = textAttribute;
        }

        @Override
        protected TextAttribute getTokenTextAttribute(IToken token) {
            return textAttribute;
        }
    }
}