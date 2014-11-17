package pl.gildur.simplepropertieseditor.editor;

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
