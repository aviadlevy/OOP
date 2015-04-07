package oop.ex7.main;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.main.Condition.*;
import oop.ex7.main.Function.*;
import oop.ex7.main.Variable.*;

public class Parser {
	private final static int VAR_NAME = 1, DEC_FUNC_NAME = 4,
			DEC_FUNC_ARGUMENTS = 5, DEC_RETURN_TYPE = 1, VAR_BRACKET = 2,
			VAR_TYPE = 3, CONDITION_LINE = 2, CALL_FUNC_NAME = 1,
			CALL_FUNC_ARGS = 2, RETURN_VALUE = 1, LEGAL_CODE = 0;
	private final static String TO_ARRAY_CLASS = "oop.ex7.main.Variable.ToArray";

	public static ArrayList<Variable> variables, argsOfFunc;
	public static ArrayList<Function> functions, currentFunction;
	public static int currentLevel;
	
	public static void parseFile(File javaCode) throws IOException, SyntaxErrorException {
		try {
			variables = new ArrayList<Variable>();
			argsOfFunc = new ArrayList<Variable>();
			functions = new ArrayList<Function>();
			currentLevel = 0;
			currentFunction = new ArrayList<Function>();
			
			// read from file:
			BufferedReader br = new BufferedReader(new FileReader(javaCode));
			String line = br.readLine();

			while (line != null) {
				// *** lines that end with semicolon sign (;) ***//
				Matcher m = RegExPatterns.SEMICOLON_END_LINE.matcher(line);
				if (m.matches()) {
					// if line contains variable declaration:
					m = RegExPatterns.VARIABLE_DECLARATION.matcher(line);
					if (m.matches()) {
						variables.add(VariableFactory.createVariable(line));
						line = br.readLine();
						continue;
					}
					// if line contains variable assignment:
					m = RegExPatterns.VARIABLE_ASSIGNMENT.matcher(line);
					if (m.matches()) {
						String tempName = m.group(VAR_NAME), 
								tempBracket = m.group(VAR_BRACKET),
								tempVal = m.group(VAR_TYPE);
						if (!GeneralCheck.arrayIndexValidation(tempBracket))
							throw new VariableErrorException();
						for (Variable variable : variables) {
							if (variable.getName().equals(tempName))
								if (variable.getClass().getName()
										.equals(TO_ARRAY_CLASS))
									tempVal = "{" + tempVal + "}";
							if (!variable.isValid(tempVal))
								throw new VariableErrorException();
							else
								break;
						}
						line = br.readLine();
						continue;
					}

					// if line contains function call:
					m = RegExPatterns.FUNCTION_CALL.matcher(line);
					if (m.matches()) {
						String args = m.group(CALL_FUNC_ARGS);
						for (Function func : functions) {
							if (func.getName().equals(m.group(CALL_FUNC_NAME))) {
								argsOfFunc = Function
										.convertArgsToVariables(func.getArgs());
								ArrayList<Variable> argsOfCall = Function
										.convertArgsToVariables(args);
								if (argsOfCall.size() != argsOfFunc.size())
									throw new FunctionErrorException();
								else 
									for (int i = 0; i < argsOfCall.size() - 1; i++) {
										if (!argsOfCall.get(i).getClass()
												.equals(argsOfFunc.get(i)
														.getClass()))
											throw new FunctionErrorException();
									}
							}
						}
						line = br.readLine();
						continue;
					}

					// if line contains a return command:
					m = RegExPatterns.RETURN_COMMAND.matcher(line);
					if (m.matches()) {
						// return type validation:
						String returnType = m.group(RETURN_VALUE);
						if (returnType != null) {
							GeneralCheck check = new GeneralCheck(currentFunction
									.get(currentFunction.size() - 1)
									.getReturnType().getPattern());
							if (check.checkExecute(returnType, currentFunction
									.get(currentFunction.size() - 1)
									.getReturnType())) {
								line = br.readLine();
								currentFunction
								.remove(currentFunction.size() - 1);
								argsOfFunc = new ArrayList<Variable>();
								continue;
							}
							// primitive type as return value:
							else if (!currentFunction
									.get(currentFunction.size() - 1)
									.getReturnType().isValid(returnType))
								throw new ReturnValueException();
						} else
							throw new ReturnValueException();
						line = br.readLine();
						currentFunction.remove(currentFunction.size() - 1);
						argsOfFunc = new ArrayList<Variable>();
						continue;
					}

				}
				// *** lines that end with open curly bracket ***//
				m = RegExPatterns.OPEN_BRACKET_END_LINE.matcher(line);
				if (m.matches()) {
					currentLevel++;
					// if \ while condition:
					m = RegExPatterns.CONDITIONS_STATEMENTS.matcher(line);
					if (m.matches()) {
						if (!Condition.checkCondition(m.group(CONDITION_LINE)))
							throw new ConditionErrorException();
						currentFunction.add(currentFunction.get(currentFunction
								.size() - 1));
						line = br.readLine();
						continue;
					}

					// if line contains function declaration:
					m = RegExPatterns.FUNCTION_DECLARATION.matcher(line);
					if (m.matches()) {
						// split function's parts and add a new Function:
						String name = m.group(DEC_FUNC_NAME), args = m
								.group(DEC_FUNC_ARGUMENTS), returnType = m
								.group(DEC_RETURN_TYPE);
						argsOfFunc = Function.convertArgsToVariables(args);
						functions.add(new Function(name, args, returnType));
						currentFunction.add(
								functions.get(functions.size() - 1));
						line = br.readLine();
						continue;
					}
				}

				// *** lines that end with close curly bracket ***//
				m = RegExPatterns.CLOSE_BRACKET_END_LINE.matcher(line);
				if (m.matches()) {
					currentLevel--;
					line = br.readLine();
					continue;
				}

				// if line contains a comment:
				m = RegExPatterns.COMMENT_LINE.matcher(line);
				if (m.matches()) {
					line = br.readLine();
					continue;
				}

				// empty line case:
				if (line.equals("")) {
					line = br.readLine();
					continue;
				} else
					// final case, if not found yet, throw an error.
					throw new SyntaxErrorException();
			}

			br.close();
			System.out.println(LEGAL_CODE);

		} catch (SyntaxErrorException e) {
			throw new SyntaxErrorException();
		} catch (IOException e) {
			throw new IOException();
		}
	}
}