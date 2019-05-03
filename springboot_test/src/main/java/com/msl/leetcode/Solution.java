package com.msl.leetcode;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/4/26 15:43
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。

 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

 在任何情况下，若函数不能进行有效的转换时，请返回 0。

 说明：

 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

 示例 1:

 输入: "42"
 输出: 42
 示例 2:

 输入: "   -42"
 输出: -42
 解释: 第一个非空白字符为 '-', 它是一个负号。
 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 示例 3:

 输入: "4193 with words"
 输出: 4193
 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 示例 4:

 输入: "words and 987"
 输出: 0
 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 因此无法执行有效的转换。
 示例 5:

 输入: "-91283472332"
 输出: -2147483648
 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 因此返回 INT_MIN (−231) 。
 */
public class Solution {

    private static final int MIN = -2147483648;
    private static final int MAX = 2147483647;
    private static final Set<String> SYMBOL_SET = new HashSet<String>(){
        {
            add("+");
            add("-");
        }
    };
    private static Set<String> NUM_SET = new HashSet<String>(){
        {
            add("0");
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
        }
    };
    private static  Set<String> A_Z = new HashSet<String>(){
        {
            add("a");add("A");
            add("b");add("B");
            add("c");add("C");
            add("d");add("D");
            add("e");add("E");
            add("f");add("F");
            add("g");add("G");
            add("h");add("H");
            add("i");add("I");
            add("j");add("J");
            add("k");add("K");
            add("l");add("L");
            add("m");add("M");
            add("n");add("N");
            add("o");add("O");
            add("p");add("P");
            add("q");add("Q");
            add("r");add("R");
            add("s");add("S");
            add("t");add("T");
            add("u");add("U");
            add("v");add("V");
            add("w");add("W");
            add("x");add("X");
            add("y");add("T");
            add("z");add("Z");
        }

    };


    public static void main(String[] args) {
//        System.out.println("42---->"+new Solution().myAtoi("42"));
//        System.out.println("+42---->"+new Solution().myAtoi("+42"));
//        System.out.println("-42---->"+new Solution().myAtoi("-42"));
//        System.out.println("4193 with words---->"+new Solution().myAtoi("4193 with words"));
//        System.out.println("words and 987---->"+new Solution().myAtoi("words and 987"));
//        System.out.println("-91283472332---->"+new Solution().myAtoi("-91283472332"));
//        System.out.println("2147483648---->"+new Solution().myAtoi("2147483648"));
//        new Solution().myAtoi("-0012a42");
//        new Solution().myAtoi("    +0a32");
//        new Solution().myAtoi("  -04f");
//        "  -0012a42"   --->  -12
//        new Solution().isAllNum("0012a4");
//           new Solution().myAtoi("  -04f");
//        System.out.println(new Solution().myAtoi("123  456"));
//        System.out.println(new Solution().myAtoi("+11e530408314"));
        System.out.println(new Solution().myAtoi("        +11245577259q"));
    }
    public int myAtoi(String str) {
        String noEmpty = str.trim();
        boolean isStartWithNum = startWithNum(noEmpty);
        boolean isEndWithNum = endWithNumber(noEmpty);
        if (noEmpty.length() == 2 && isStartWithNum && isEndWithNum) {
            return Integer.parseInt(noEmpty);
        }
        if (noEmpty.length() == 1 && isStartWithNum ) {
            return Integer.parseInt(noEmpty);
        }
        //如果数字  或   ("+"开头 且 不能只有一个"+"
        if (isStartWithNum || isOnlyAdd(noEmpty)) {
            return getNumStartNum(noEmpty,true);
        }

        //如果   - 开头
        if (isOnlyMin(noEmpty)) {
            return getNumStartNum(noEmpty,false);
        }

        //其他字母开头
        return 0;

    }

    private boolean isOnlyAdd(String noEmpty) {
        //必须是加号开头并且加号后面是数字
        return noEmpty.length() > 1 && noEmpty.startsWith("+") && NUM_SET.contains(String.valueOf(noEmpty.charAt(1)));
    }

    private boolean isOnlyMin(String noEmpty) {
        //必须是减号开头并且加号后面紧跟数字
        return noEmpty.length() > 1 && noEmpty.startsWith("-") && NUM_SET.contains(String.valueOf(noEmpty.charAt(1)));
    }

    private boolean isAllNum(String str) {
        final char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (!NUM_SET.contains(String.valueOf(aChar)) && !String.valueOf(aChar).equals(".") && !String.valueOf(aChar).equals(" ")) {
                return false;
            }
        }
        return true;
    }


    private int getIndex(String str) {
        final char[] chars = str.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            //如果当前不是数字 则返回下标
            if(!NUM_SET.contains(String.valueOf(chars[i]))) {
                return i;
            }
        }
        throw new RuntimeException("末尾是数字结尾！");
    }


    private boolean startWithNum(String str) {
        for (String s : NUM_SET) {
            if (str.startsWith(s) ) {
                return true;
            }
        }
        return false;
    }


    private boolean endWithNumber(String str) {
        for (String s : NUM_SET ) {
            if (str.endsWith(s)) {

                return true;
            }
        }
        return false;
    }

    private int getNumStartNum (String noEmpty,boolean plusOrMinus) {
        double num = 0;
        //数字结尾
        try {
            if (endWithNumber(noEmpty)) {
                //中间不能有符号  “-0012a42”
                if (!isAllNum(String.valueOf(noEmpty.substring(1,noEmpty.length()-1)))) {
                    int lastNumindex = getIndex(noEmpty);
                    num = Double.parseDouble(noEmpty.substring(0,lastNumindex));
                } else {
                    num = Double.parseDouble(noEmpty);
                }
            } else {
                //非数字结尾找到最后一个数字的下标
                int lastNumindex = getIndex(noEmpty);
                num = Double.parseDouble(noEmpty.substring(0,lastNumindex));
            }
        } catch (NumberFormatException e) {
            int lastNumindex = getIndex(noEmpty);
            num = Double.parseDouble(noEmpty.substring(0,lastNumindex));
        }
        return (int) (plusOrMinus ? num > MAX ? MAX : num : num < MIN ? MIN : num);
    }

    /**
     * 成功
     显示详情
     执行用时 : 23 ms, 在String to Integer (atoi)的Java提交中击败了70.10% 的用户
     内存消耗 : 36.4 MB, 在String to Integer (atoi)的Java提交中击败了83.73% 的用户
     进行下一个挑战：
     */
}
