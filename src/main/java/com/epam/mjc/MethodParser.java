package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        StringSplitter ss = new StringSplitter();

        String delimiterBeforeBrace = "(";

        StringTokenizer st = new StringTokenizer(signatureString, delimiterBeforeBrace);

        int i = 0;

        String[] before = new String[0];
        String[] after = new String[0];
        boolean isThereAccessModifier = false;
        boolean isThereArguments = false;
        int argumentsNumber = 0;

        while (st.hasMoreTokens()) {
            i++;

            String next = st.nextToken();
            System.out.println(next);

            List<String> nexts = ss.splitByDelimiters(next, new ArrayList<>(List.of(" ", "(", ")", ",")));
            System.out.println(nexts);

            if (i == 1 && nexts.size() == 2) {
                isThereAccessModifier = false;
                before = new String[nexts.size()];
                for (int j = 0; j < nexts.size(); j++) {
                    before[j] = nexts.get(j);
                }
            } else if (i == 1 && nexts.size() == 3) {
                isThereAccessModifier = true;
                before = new String[nexts.size()];
                for (int k = 0; k < nexts.size(); k++) {
                    before[k] = nexts.get(k);
                }
            } else if (i == 2) {
                if (nexts.size() > 0) {
                    isThereArguments = true;
                    argumentsNumber = nexts.size() / 2;
                }
                after = new String[nexts.size()];
                for (int c = 0; c < nexts.size(); c++) {
                    after[c] = nexts.get(c);
                }
            }
        }
        System.out.println(Arrays.toString(before));
        System.out.println(Arrays.toString(after));
        System.out.println("isThereAccessModifier " + isThereAccessModifier);
        System.out.println("isThereArguments " + isThereArguments);
        System.out.println("argumentsNumber " + argumentsNumber);

        MethodSignature ms;
        List<MethodSignature.Argument> arguments = new ArrayList<>(argumentsNumber);
        for (int a = 0; a < argumentsNumber * 2; a += 2) {
            arguments.add(new MethodSignature.Argument(after[a], after[a + 1]));
        }

        if (isThereArguments) {
            if (isThereAccessModifier) {
                ms = new MethodSignature(before[2], arguments);
                ms.setAccessModifier(before[0]);
                ms.setReturnType(before[1]);
            } else {
                ms = new MethodSignature(before[1], arguments);
                ms.setReturnType(before[0]);
            }

            System.out.println(Arrays.toString(arguments.toArray()));
            System.out.println(ms);
        } else {
            if (isThereAccessModifier) {
                ms = new MethodSignature(before[2]);
                ms.setAccessModifier(before[0]);
                ms.setReturnType(before[1]);
            } else {
                ms = new MethodSignature(before[1]);
                ms.setReturnType(before[0]);
            }

            System.out.println(Arrays.toString(arguments.toArray()));
            System.out.println(ms);
        }

        return ms;
//        throw new UnsupportedOperationException("You should implement this method.");
    }
}
