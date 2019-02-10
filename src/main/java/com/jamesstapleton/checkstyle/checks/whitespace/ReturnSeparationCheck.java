////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2018 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.jamesstapleton.checkstyle.checks.whitespace;

import com.puppycrawl.tools.checkstyle.StatelessCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * This Check checks to see if a multi-line statement is properly padded by an empty lines on either
 * side of it.
 *
 * <p>valid:
 * <pre>
 *   public String foo() {
 *      System.o
 *   }
 *
 *   public String bar() {
 *      String f = foo();
 *
 *      return f;
 *   }
 *
 *   public String baz() {
 *      String f = bar();
 *
 *      if (f.isEmpty()) {
 *          return "empty";
 *      }
 *
 *      return f;
 *   }
 * </pre>
 *
 * <p>invalid:
 * <pre>
 *   public String bar() {
 *      String f = foo();
 *      return f;
 *   }
 *
 *   public String baz() {
 *      String f = bar();
 *
 *      if (f.isEmpty()) {
 *          f = "empty";
 *      }
 *      return f;
 *   }
 * </pre>
 */
@StatelessCheck
public class ReturnSeparationCheck extends AbstractCheck {
    /**
     * The language key to use for the check error.
     */
    public static final String MSG_EMPTY_LINE_BEFORE_RETURN = "noEmptyLineBeforeReturn";

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {TokenTypes.LITERAL_RETURN};
    }

    @Override
    public int[] getRequiredTokens() {
        return getAcceptableTokens();
    }

    @Override
    public void visitToken(DetailAST token) {
        if (!isFirstInBlock(token) && !hasEmptyLineBefore(token)) {
            log(token, MSG_EMPTY_LINE_BEFORE_RETURN);
        }
    }

    /**
     * Checks if a token has a empty line before.
     * @param token token.
     * @return true, if token have empty line before.
     */
    private boolean hasEmptyLineBefore(DetailAST token) {
        // In Java, the line number is always > 1 due to 'isFirstInBlock' check will prevent this
        // block from being executed.
        final int lineNo = token.getLineNo();
        final FileContents fileContents = getFileContents();

        // empirically, lineIsBlank is 0 based, whereas hasIntersectionWithComment is 1 based.
        return fileContents.lineIsBlank(lineNo - 2)
            || fileContents.hasIntersectionWithComment(
                lineNo - 1, token.getColumnNo(), lineNo - 1, token.getColumnNo());
    }

    /**
     * Checks if a token is the first within its block.
     * @param token token.
     * @return true, if token is the first within its block
     */
    private static boolean isFirstInBlock(DetailAST token) {
        DetailAST currentToken = token.getPreviousSibling();

        while (currentToken != null && currentToken.getLineNo() == token.getLineNo()) {
            currentToken = currentToken.getPreviousSibling();
        }

        return currentToken == null;
    }
}
