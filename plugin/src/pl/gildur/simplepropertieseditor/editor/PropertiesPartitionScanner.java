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
