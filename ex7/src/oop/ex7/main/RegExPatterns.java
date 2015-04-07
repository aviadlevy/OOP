package oop.ex7.main;
import java.util.regex.Pattern;

/**
 * A collection of the regex patterns for most of program.
 */
public class RegExPatterns {
	public final static String
	TYPE = "int|double|boolean|char|String",
	VAR_NAME = "_\\w+|[a-zA-Z]\\w*",
	FUNC_NAME = "[a-zA-Z]\\w*",
	RETURN_TYPES = "void|int|double|boolean|char|String",
	OPERATORS = "\\+|\\-|\\*|\\/";

	// parsing regex patterns:
	public static final Pattern 
	SEMICOLON_END_LINE = Pattern.compile("^.+;\\s*$"),
	VARIABLE_DECLARATION = Pattern.compile("^\\s*(" + TYPE
					+ ")(\\[\\])?\\s+.*(\\s*=\\s*\\w*)?\\s*;\\s*$"),
	VARIABLE_ASSIGNMENT = Pattern.compile("^\\s*(" + VAR_NAME
					+ ")\\[(.*)\\]\\s*=\\s*(.+)\\s*;\\s*$"),
	FUNCTION_CALL = Pattern.compile("^\\s*(" + FUNC_NAME +
			")\\s*\\((.*)\\)\\s*;\\s*$"),
	FUNCTION_DECLARATION = Pattern.compile("^\\s*((" + RETURN_TYPES + ")(\\[\\])?)"
			+ "\\s+("+ FUNC_NAME + ")\\((.*)\\)\\s*\\{\\s*$"),
	RETURN_COMMAND = Pattern.compile("^\\s*return\\s*(.*);\\s*$"),
	CONDITIONS_STATEMENTS = 
	Pattern.compile("^\\s*(if|while)\\s*\\((.+)\\)\\s*\\{\\s*$"),
	OPEN_BRACKET_END_LINE = Pattern.compile("^.+\\{$"),
	CLOSE_BRACKET_END_LINE = Pattern.compile("^.*\\}$"),
	COMMENT_LINE = Pattern.compile("^\\s*\\/\\/.*$"),
	
	VARIABLE_GENERAL_PATTERN = Pattern.compile("^\\s*(" + TYPE
					+ ")(\\[\\])?\\s+(\\S*)(\\s*=\\s*(.*))?\\s*;\\s*$"),
	CONDITION_LINE = Pattern.compile("^\\s*(" + VAR_NAME + ")(\\[.*\\])?\\s*$"),
	LEGAL_MATH_EXPRESSION = Pattern.compile("^\\-?\\d+[\\+\\-\\*\\/]\\d+$"),
	ILLEGAL_MATH_EXPRESSION = Pattern.compile("^[\\+\\-\\*\\/]\\d+$"),
	INSTANCE_ARRAY_INSERT = Pattern.compile("\\s*\\{(.*)\\}\\s*"),

	// types' regex:
	INT = Pattern.compile("^\\s*-?\\d+\\s*$"),
	DOUBLE = Pattern.compile("^\\s*-?\\d+([.]\\d+)?\\s*$"),
	STRING = Pattern.compile("^\\s*\".*\"\\s*$"),
	CHAR = Pattern.compile("^\\s*'.?'\\s*$"),
	BOOLEAN = Pattern.compile("^\\s*true\\s*$|^\\s*false\\s*$");
}
