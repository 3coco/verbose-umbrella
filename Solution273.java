class Solution {
    private String[] num2Str_sm = {"Zero", "One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
        "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    private String[] num2Str_md = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    private String[] num2Str_lg = {
        "Billion", "Million", "Thousand", ""
    };
    
    private String num2Str(int x) {
        StringBuilder sb = new StringBuilder();
        if (x >= 100) {
            sb.append(num2Str_sm[x / 100]).append(" Hundred ");
            x %= 100; 
        }
        if (x >= 20) {
            sb.append(num2Str_md[x / 10]).append(" ");
            x %= 10;
        }
        if (x != 0) {
            sb.append(num2Str_sm[x]).append(" ");
        }
        return sb.toString();
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return num2Str_sm[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (int)1e9, j = 0; i >= 1; i /= 1000, j++) {
            if (num < i) {
                continue;
            }
            sb.append(num2Str(num / i)).append(num2Str_lg[j]).append(" ");
            num %= i;
        }
        while(sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
