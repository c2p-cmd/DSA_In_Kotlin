package linear.stack.dynamic;

import java.util.*;

public class StackProblems {
    private static final Map<Character, Character> matchingParenMap = new HashMap<>();
    private static final Set<Character> openingParenSet = new HashSet<>();

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
    }

    static {
        matchingParenMap.put(')','(');
        matchingParenMap.put(']','[');
        matchingParenMap.put('}','{');

        openingParenSet.addAll(matchingParenMap.values());
    }

    private static void problem1() {
        // has matching parenthesis
        final String[] expressions = {"{ab}", "{(abg}", "(ab[gd])", "{[()[}", "{{}}([]){}{}{}{}{[[[[]]]]}", "{{{}}]()", "({{{}}})"};

        for (final String expression : expressions) {
            System.out.println("Does " + expression + " have matching parenthesis?: " +
                    hasMatchingParens(expression)
            );
        }
    }

    private static void problem2() {
        // minimum stack problem
        final int[] numbers = {2, 4, 10, 1, 11, -2};
        final MinimumStack<Integer> minStack = new MinimumStack<>();

        try {
            for (final int n : numbers) {
                minStack.push(n);
                System.out.println("Current minimum in stack: " + minStack.getMinimumElement());
            }

            minStack.pop();
            System.out.println("Current minimum in stack: " + minStack.getMinimumElement());
            minStack.pop();
            System.out.println("Current minimum in stack: " + minStack.getMinimumElement());
            minStack.pop();
            System.out.println("Current minimum in stack: " + minStack.getMinimumElement());
            minStack.pop();
            System.out.println("Current minimum in stack: " + minStack.getMinimumElement());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean hasMatchingParens(String expression) {
        try {
            final Stack<Character> parenthesisStack = new Stack<>();

            for (Character c : expression.toCharArray()) {
                if (openingParenSet.contains(c))
                    parenthesisStack.push(c);

                if (matchingParenMap.containsKey(c)) {
                    char lastAddedParen = parenthesisStack.pop();

                    if (lastAddedParen != matchingParenMap.get(c))
                        return false;
                }
            }

            return parenthesisStack.isEmpty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
