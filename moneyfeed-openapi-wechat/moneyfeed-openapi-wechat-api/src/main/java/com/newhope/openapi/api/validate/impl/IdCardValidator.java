package com.newhope.openapi.api.validate.impl;


import com.google.common.collect.Lists;
import com.newhope.openapi.api.validate.IdCard;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 身份证号码验证规则
 * 1、将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。
 * 2、将这17位数字和系数相乘的结果相加。
 * 3、用加出来和除以11，看余数是多少？
 * 4、余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。其分别对应的最后一位身份证的号码为1－0－X －9－8－7－6－5－4－3－2。(即余数0对应1，余数1对应0，余数2对应X...)
 * 5、通过上面得知如果余数是3，就会在身份证的第18位数字上出现的是9。如果对应的数字是2，身份证的最后一位号码就是罗马数字x。
 * 例如：某男性的身份证号码为【53010219200508011x】， 我们看看这个身份证是不是合法的身份证。
 * 首先我们得出前17位的乘积和【(5*7)+(3*9)+(0*10)+(1*5)+(0*8)+(2*4)+(1*2)+(9*1)+(2*6)+(0*3)+(0*7)+(5*9)+(0*10)+(8*5)+(0*8)+(1*4)+(1*2)】是189，
 * 然后用189除以11得出的结果是189/11=17----2，也就是说其余数是2。最后通过对应规则就可以知道余数2对应的检验码是X。所以，可以判定这是一个正确的身份证号码。
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    private static final int CARD_LENGTH = 18;
    private static final List<Integer> MODULUS = Lists.newArrayList(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
    private static final List<String> PARITY = Lists.newArrayList("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    private static final List<Integer> REMAINDER = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Override
    public void initialize(IdCard idCard) {

    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(cardNumber)) {
            return true;
        }
        if (cardNumber.length() != CARD_LENGTH) {
            return false;
        }
        String lastChar = String.valueOf(cardNumber.charAt(cardNumber.length() - 1));
        List<String> removed = Lists.newArrayList(cardNumber.substring(0, cardNumber.length() - 1).split(""));
        Integer sum = 0;
        for (int i = 0; i < removed.size(); i++) {
            try {
                sum += Integer.parseInt(removed.get(i)) * MODULUS.get(i);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        Integer remainder = sum % 11;
        int position = getPosition(remainder);
        if (position == -1) {
            return false;
        }
        return PARITY.get(position).equalsIgnoreCase(lastChar);
    }

    private int getPosition(Integer number) {
        for (int i = 0; i < REMAINDER.size(); i++) {
            if (REMAINDER.get(i).equals(number)) {
                return i;
            }
        }
        return -1;
    }
}
