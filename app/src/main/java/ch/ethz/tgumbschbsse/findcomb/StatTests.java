package ch.ethz.tgumbschbsse.findcomb;


public class StatTests {
    private int n11, n12, n21, n22;
    public double p, logp;

    StatTests(int n11, int n12, int n21, int n22){
        p = factorial(n11+n12) * factorial(n21 + n22) / (factorial(n11)*factorial(n12));
        p *= factorial(n11+n21) * factorial(n12+n22) / (factorial(n21)*factorial(n22));
        p /= factorial(n11+n12+n21+n22);

        //System.out.println(String.valueOf(p));
        logp = -1*Math.log(p);

    }


    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

}
