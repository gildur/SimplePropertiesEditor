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

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IDocumentPartitioningListener;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;

public class PropertiesDocumentWrapper implements IDocument {

    private IDocument document;

    public PropertiesDocumentWrapper(IDocument document) {
        this.document = document;
    }

    @Override
    public void addDocumentListener(IDocumentListener arg0) {
        document.addDocumentListener(arg0);
    }

    @Override
    public void addDocumentPartitioningListener(IDocumentPartitioningListener arg0) {
        document.addDocumentPartitioningListener(arg0);
    }

    @Override
    public void addPosition(Position arg0) throws BadLocationException {
        document.addPosition(arg0);
    }

    @Override
    public void addPosition(String arg0, Position arg1) throws BadLocationException, BadPositionCategoryException {
        document.addPosition(arg0, arg1);
    }

    @Override
    public void addPositionCategory(String arg0) {
        document.addPositionCategory(arg0);
    }

    @Override
    public void addPositionUpdater(IPositionUpdater arg0) {
        document.addPositionUpdater(arg0);
    }

    @Override
    public void addPrenotifiedDocumentListener(IDocumentListener arg0) {
        document.addPrenotifiedDocumentListener(arg0);
    }

    @Override
    public int computeIndexInCategory(String arg0, int arg1) throws BadLocationException, BadPositionCategoryException {
        return document.computeIndexInCategory(arg0, arg1);
    }

    @Override
    public int computeNumberOfLines(String arg0) {
        return document.computeNumberOfLines(arg0);
    }

    @Override
    public ITypedRegion[] computePartitioning(int arg0, int arg1) throws BadLocationException {
        return document.computePartitioning(arg0, arg1);
    }

    @Override
    public boolean containsPosition(String arg0, int arg1, int arg2) {
        return document.containsPosition(arg0, arg1, arg2);
    }

    @Override
    public boolean containsPositionCategory(String arg0) {
        return document.containsPositionCategory(arg0);
    }

    @Override
    public String get() {
        String content = document.get();
        StringBuffer buffer = new StringBuffer();
        Charset asciiCharset = Charset.forName("US-ASCII");
        ByteBuffer encodedUnknown = asciiCharset.encode("?");
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c != '?' && asciiCharset.encode(String.valueOf(c)).equals(encodedUnknown)) {
                buffer.append(String.format("\\u%04X", (int) c));
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    @Override
    public String get(int arg0, int arg1) throws BadLocationException {
        return document.get(arg0, arg1);
    }

    @Override
    public char getChar(int arg0) throws BadLocationException {
        return document.getChar(arg0);
    }

    @Override
    public String getContentType(int arg0) throws BadLocationException {
        return document.getContentType(arg0);
    }

    @Override
    public IDocumentPartitioner getDocumentPartitioner() {
        return document.getDocumentPartitioner();
    }

    @Override
    public String[] getLegalContentTypes() {
        return document.getLegalContentTypes();
    }

    @Override
    public String[] getLegalLineDelimiters() {
        return document.getLegalLineDelimiters();
    }

    @Override
    public int getLength() {
        return document.getLength();
    }

    @Override
    public String getLineDelimiter(int arg0) throws BadLocationException {
        return document.getLineDelimiter(arg0);
    }

    @Override
    public IRegion getLineInformation(int arg0) throws BadLocationException {
        return document.getLineInformation(arg0);
    }

    @Override
    public IRegion getLineInformationOfOffset(int arg0) throws BadLocationException {
        return document.getLineInformationOfOffset(arg0);
    }

    @Override
    public int getLineLength(int arg0) throws BadLocationException {
        return document.getLineLength(arg0);
    }

    @Override
    public int getLineOfOffset(int arg0) throws BadLocationException {
        return document.getLineOfOffset(arg0);
    }

    @Override
    public int getLineOffset(int arg0) throws BadLocationException {
        return document.getLineOffset(arg0);
    }

    @Override
    public int getNumberOfLines() {
        return document.getNumberOfLines();
    }

    @Override
    public int getNumberOfLines(int arg0, int arg1) throws BadLocationException {
        return document.getNumberOfLines(arg0, arg1);
    }

    @Override
    public ITypedRegion getPartition(int arg0) throws BadLocationException {
        return document.getPartition(arg0);
    }

    @Override
    public String[] getPositionCategories() {
        return document.getPositionCategories();
    }

    @Override
    public IPositionUpdater[] getPositionUpdaters() {
        return document.getPositionUpdaters();
    }

    @Override
    public Position[] getPositions(String arg0) throws BadPositionCategoryException {
        return document.getPositions(arg0);
    }

    @Override
    public void insertPositionUpdater(IPositionUpdater arg0, int arg1) {
        document.insertPositionUpdater(arg0, arg1);
    }

    @Override
    public void removeDocumentListener(IDocumentListener arg0) {
        document.removeDocumentListener(arg0);
    }

    @Override
    public void removeDocumentPartitioningListener(IDocumentPartitioningListener arg0) {
        document.removeDocumentPartitioningListener(arg0);
    }

    @Override
    public void removePosition(Position arg0) {
        document.removePosition(arg0);
    }

    @Override
    public void removePosition(String arg0, Position arg1) throws BadPositionCategoryException {
        document.removePosition(arg0, arg1);
    }

    @Override
    public void removePositionCategory(String arg0) throws BadPositionCategoryException {
        document.removePositionCategory(arg0);
    }

    @Override
    public void removePositionUpdater(IPositionUpdater arg0) {
        document.removePositionUpdater(arg0);
    }

    @Override
    public void removePrenotifiedDocumentListener(IDocumentListener arg0) {
        document.removePrenotifiedDocumentListener(arg0);
    }

    @Override
    public void replace(int arg0, int arg1, String arg2) throws BadLocationException {
        document.replace(arg0, arg1, arg2);
    }

    @Override
    @SuppressWarnings("deprecation")
    public int search(int arg0, String arg1, boolean arg2, boolean arg3, boolean arg4) throws BadLocationException {
        return document.search(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public void set(String arg0) {
        document.set(arg0);
    }

    @Override
    public void setDocumentPartitioner(IDocumentPartitioner arg0) {
        document.setDocumentPartitioner(arg0);
    }
}
