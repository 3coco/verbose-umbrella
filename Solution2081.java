class Solution2081 {
    public long kMirror(int k, int n) {
        int len = 1;
        long res = 0;
        while (n > 0) {
            long start = (long)Math.pow(10, len - 1);
            long end = (long)Math.pow(10, len);
            for (long i = start; n > 0 && i < end; i++) {
                long m10 = getMirror(i, false);
                if (isMirrorInBaseK(m10, k)) {
                    res += m10;
                    n--;
                }
            }
            for (long i = start; n > 0 && i < end; i++) {
                long m10 = getMirror(i, true);
                if (isMirrorInBaseK(m10, k)) {
                    res += m10;
                    n--;
                }
            }
            len++;
        }
        return res;
    }

    private long getMirror(long num, boolean flag) {
        int count = 0;
        long n = num;
        long m = 0;
        while (n != 0) {
            m *= 10;
            m += (n % 10);
            n /= 10;
            count++;
        }
        return flag ? num * (long)Math.pow(10, count) + m : num / 10l * (long)Math.pow(10, count) + m;
    }

    private boolean isMirrorInBaseK(long num, int k) {
        long mul = 1;
        while (k * mul <= num) {
            mul *= k;
        }
        while (num != 0) {
            if (num / mul != num % k) {
                return false;
            }
            num = (num - (num / mul) * mul) / k;
            mul /= (k * k);
        }
        return true;
    }
}
