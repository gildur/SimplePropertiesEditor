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
package pl.gildur.simplepropertieseditor.editor;

import org.eclipse.jface.text.rules.*;

public class PropertiesPartitionScanner extends RuleBasedPartitionScanner {

    public final static String PROPERTIES_COMMENT = "__properties_comment";

    public final static String PROPERTIES_ENTRY = "__properties_entry";

    public PropertiesPartitionScanner() {

        IToken comment = new Token(PROPERTIES_COMMENT);
        IToken entry = new Token(PROPERTIES_ENTRY);

        IPredicateRule[] rules = new IPredicateRule[2];

        SingleLineRule commentRule = new SingleLineRule("#", null, comment, (char) 0, true);
        commentRule.setColumnConstraint(0);
        rules[0] = commentRule;
        rules[1] = new SingleLineRule("=", null, entry, (char) 0, true);

        setPredicateRules(rules);
    }
}
