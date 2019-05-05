package com.msl.leetcode;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/4/28 10:41
 */
public class StringAdd {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length()-1, j = num2.length()-1;
        while(i >= 0 || j >= 0 || carry != 0){
            if(i>=0) carry += num1.charAt(i--)-'0';
            if(j>=0) carry += num2.charAt(j--)-'0';

            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new StringAdd().addStrings("186", "14"));
//        System.out.println(new StringBuilder("abcdefg").reverse());

    }
}
