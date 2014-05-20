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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class PropertiesDocumentProvider extends FileDocumentProvider {

    @Override
    protected IDocument createDocument(Object element) throws CoreException {
        IDocument document = super.createDocument(element);
        if (document != null) {
            IDocumentPartitioner partitioner = new FastPartitioner(new PropertiesPartitionScanner(),
                            new String[] { PropertiesPartitionScanner.PROPERTIES_ENTRY,
                                            PropertiesPartitionScanner.PROPERTIES_COMMENT });
            partitioner.connect(document);
            document.setDocumentPartitioner(partitioner);
        }

        String content = document.get();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '\\' && i < content.length() - 1 && content.charAt(i + 1) == 'u') {
                int code = Integer.parseInt(content.substring(i + 2, i + 6), 16);
                buffer.append((char) code);
                i += 5;
            } else {
                buffer.append(c);
            }
        }
        document.set(buffer.toString());

        return document;
    }

    @Override
    protected void doSaveDocument(IProgressMonitor monitor, Object element, final IDocument document, boolean overwrite)
                    throws CoreException {
        super.doSaveDocument(monitor, element, new PropertiesDocumentWrapper(document), overwrite);
    }
}
