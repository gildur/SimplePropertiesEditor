// Copyright (c) 2010 Piotr Wolny
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without
// restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following
// conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.
package org.gildur.simplepropertieseditor.editor;

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