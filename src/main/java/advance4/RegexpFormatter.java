package advance4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpFormatter {


    public static void formatMobileNumber(String str) {
        String normalStr = str;
        Pattern pattern = Pattern.compile("(\\+\\d)(\\(\\d{3}\\))([ ]\\d{3}[ ]\\d{2}[ ]\\d{2})");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String mobilePhone = matcher.group();
            String correctMobilePhone = mobilePhone.replaceAll("\\W", "");
            normalStr = normalStr.replace(mobilePhone, correctMobilePhone);
            System.out.println(correctMobilePhone);
        }
    }
}

