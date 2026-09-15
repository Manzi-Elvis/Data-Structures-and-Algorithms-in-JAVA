/**
 * 10-Mathematics.java
 *
 * A handful of number-theory primitives that quietly underpin a huge
 * fraction of "hard" problems -- not because the math is hard, but
 * because most people never see WHY these algorithms work, only THAT
 * they work. Understanding the why is what lets you adapt them.
 *
 * Run:  javac 10-Mathematics.java -d out && java -cp out Mathematics
 */
class Mathematics {

    // ---- GCD via the Euclidean algorithm ---------------------------------
    // The key insight: gcd(a, b) = gcd(b, a % b). Why? Any number that
    // divides both a and b must also divide (a - b), and repeatedly
    // subtracting b from a is exactly what a % b computes in bulk.
    // So the GCD never changes -- we just shrink the numbers toward it.
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // ---- LCM, built directly from GCD --------------------------------------
    // lcm(a, b) * gcd(a, b) = a * b always holds. Rearranged:
    // lcm(a, b) = (a * b) / gcd(a, b). We divide FIRST to reduce overflow risk.
    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // ---- Sieve of Eratosthenes: all primes up to n ------------------------
    // Instead of checking each number for primality individually (slow),
    // start with everything marked "prime" and cross OFF multiples of each
    // prime as you find it. By the time you reach a number that's still
    // marked prime, nothing smaller has been able to cross it off --
    // which is precisely what makes it prime.
    static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        java.util.Arrays.fill(isPrime, true);
        isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int i = 2; (long) i * i <= n; i++) {
            if (isPrime[i]) {
                // Start crossing off at i*i, not 2*i: smaller multiples of i
                // (like 2*i, 3*i) were already crossed off by smaller primes.
                for (int multiple = i * i; multiple <= n; multiple += i) {
                    isPrime[multiple] = false;
                }
            }
        }
        return isPrime;
    }

    // ---- Prime factorization by trial division -----------------------------
    // Divide out every factor of 2, then every odd number up to sqrt(n).
    // We only need to check up to sqrt(n) because if n had a factor
    // LARGER than sqrt(n), it would have to pair with a factor SMALLER
    // than sqrt(n) -- which we'd have already found.
    static java.util.List<Long> primeFactorize(long n) {
        java.util.List<Long> factors = new java.util.ArrayList<>();
        while (n % 2 == 0) {
            factors.add(2L);
            n /= 2;
        }
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n); // whatever's left is itself prime
        return factors;
    }

    // ---- Fast exponentiation (binary exponentiation) ------------------------
    // Naive power(base, exp) is O(exp) -- exp multiplications in a row.
    // Fast exponentiation is O(log exp): square the base each step and
    // only multiply it into the result when the current bit of exp is 1.
    // This mirrors exactly how "38 = 32 + 4 + 2" decomposes into powers of 2.
    static long fastPower(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {          // if the lowest bit of exp is 1
                result = (result * base) % mod;
            }
            base = (base * base) % mod;    // square the base
            exp >>= 1;                     // move to the next bit
        }
        return result;
    }

    // ---- Modular arithmetic building blocks -----------------------------------
    // Why we need this at all: results in competitive programming / DP over
    // huge counts are usually taken "mod 1_000_000_007" (a large prime) to
    // keep numbers within a long. Addition and multiplication distribute
    // over mod cleanly -- subtraction needs a nudge to stay non-negative.
    static final long MOD = 1_000_000_007L;

    static long modAdd(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    static long modMul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static long modSub(long a, long b) {
        // Adding MOD before the final mod prevents a negative result
        // (Java's % can return negative values for negative operands).
        return ((a % MOD) - (b % MOD) + MOD) % MOD;
    }

    public static void main(String[] args) {
        System.out.println("=== GCD / LCM ===");
        System.out.println("gcd(48, 18) = " + gcd(48, 18) + " (expect 6)");
        System.out.println("lcm(4, 6)   = " + lcm(4, 6) + " (expect 12)");

        System.out.println("\n=== Sieve of Eratosthenes (primes up to 50) ===");
        boolean[] isPrime = sieveOfEratosthenes(50);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= 50; i++) {
            if (isPrime[i]) sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());

        System.out.println("\n=== Prime factorization ===");
        long[] toFactor = {360, 97, 1000000};
        for (long n : toFactor) {
            System.out.println(n + " = " + primeFactorize(n));
        }

        System.out.println("\n=== Fast exponentiation ===");
        System.out.println("2^10 mod 1_000_000_007 = " + fastPower(2, 10, MOD) + " (expect 1024)");
        System.out.println("7^1000000 mod 1_000_000_007 = " + fastPower(7, 1_000_000, MOD)
                + "  <- computed via ~20 squarings, not a million multiplications");

        System.out.println("\n=== Modular arithmetic ===");
        System.out.println("modAdd(MOD-1, 5) = " + modAdd(MOD - 1, 5));
        System.out.println("modSub(3, 10)    = " + modSub(3, 10) + " (stays non-negative)");
    }
}