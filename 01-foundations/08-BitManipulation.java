/**
 * 08-BitManipulation.java
 *
 * Bit tricks look like magic until you realize they're all built from
 * about six ideas, combined. This file demonstrates each idea in
 * isolation, then shows the "aha" combinations that solve real problems.
 *
 * Run:  javac 08-BitManipulation.java -d out && java -cp out BitManipulation
 */
class BitManipulation {

    // ---- The core operators, made visible -------------------------------
    static void showBasicOps(int a, int b) {
        System.out.printf("a = %d (%8s)%n", a, Integer.toBinaryString(a));
        System.out.printf("b = %d (%8s)%n", b, Integer.toBinaryString(b));
        System.out.printf("a & b  = %d (%8s)   AND: 1 only where BOTH bits are 1%n", a & b, Integer.toBinaryString(a & b));
        System.out.printf("a | b  = %d (%8s)   OR:  1 where EITHER bit is 1%n", a | b, Integer.toBinaryString(a | b));
        System.out.printf("a ^ b  = %d (%8s)   XOR: 1 where bits DIFFER%n", a ^ b, Integer.toBinaryString(a ^ b));
        System.out.printf("~a     = %d              NOT: flips every bit%n", ~a);
        System.out.printf("a << 1 = %d (%8s)   left shift: multiply by 2%n", a << 1, Integer.toBinaryString(a << 1));
        System.out.printf("a >> 1 = %d (%8s)   right shift: divide by 2 (floor)%n", a >> 1, Integer.toBinaryString(a >> 1));
    }

    // ---- Check if the i-th bit is set ------------------------------------
    // Shift a single 1-bit to position i, then AND. If that bit was set in
    // n, the result is non-zero.
    static boolean isBitSet(int n, int i) {
        return (n & (1 << i)) != 0;
    }

    // ---- Set the i-th bit --------------------------------------------------
    static int setBit(int n, int i) {
        return n | (1 << i);
    }

    // ---- Clear the i-th bit ------------------------------------------------
    // ~(1 << i) is all 1s except a single 0 at position i. ANDing with it
    // forces that position to 0 and leaves everything else untouched.
    static int clearBit(int n, int i) {
        return n & ~(1 << i);
    }

    // ---- Toggle the i-th bit ------------------------------------------------
    static int toggleBit(int n, int i) {
        return n ^ (1 << i);
    }

    // ---- Check if a number is a power of 2 -----------------------------
    // A power of 2 has EXACTLY one bit set (e.g. 8 = 1000).
    // n-1 flips that single bit to 0 and all bits below it to 1
    // (8 = 1000, 7 = 0111). ANDing them together gives 0 ONLY when
    // n had exactly one bit set to begin with.
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // ---- Count set bits (Brian Kernighan's algorithm) --------------------
    // n & (n-1) clears the LOWEST set bit each time. Repeating this until
    // n becomes 0 counts exactly as many times as there were set bits --
    // this is O(number of set bits), not O(number of total bits), which
    // matters when n is sparse.
    static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);   // clears the lowest set bit
            count++;
        }
        return count;
    }

    // ---- Find the single number that doesn't repeat ------------------------
    // Classic XOR trick: x ^ x = 0, and x ^ 0 = x, and XOR is commutative/
    // associative. So XORing every element together cancels every PAIR
    // out completely, leaving only the element with no pair.
    static int findSingleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) {
            result ^= n;
        }
        return result;
    }

    // ---- Swap two integers without a temp variable -------------------------
    // A cute trick, rarely the *right* choice in real code (readability
    // loses to a temp variable), but a genuine demonstration of what XOR's
    // self-canceling property enables.
    static int[] xorSwap(int a, int b) {
        a = a ^ b;
        b = a ^ b;   // b = (a^b)^b = a
        a = a ^ b;   // a = (a^b)^a = b
        return new int[]{a, b};
    }

    // ---- Get the lowest set bit as its own value ----------------------------
    // n & (-n) isolates the lowest set bit. This works because -n in two's
    // complement is (~n + 1), and that construction guarantees everything
    // below the lowest set bit of n becomes 0 in -n, while the lowest set
    // bit itself and everything above it flips such that ANDing recovers
    // exactly that one bit. This exact trick powers Fenwick Trees
    // (see 24-advanced-data-structures/02-FenwickTree.java).
    static int lowestSetBit(int n) {
        return n & (-n);
    }

    public static void main(String[] args) {
        System.out.println("=== Basic operators ===");
        showBasicOps(12, 10);

        System.out.println("\n=== Bit manipulation primitives ===");
        int n = 0b1010; // 10
        System.out.println("n = " + Integer.toBinaryString(n) + " (" + n + ")");
        System.out.println("isBitSet(n, 1) = " + isBitSet(n, 1) + "  (bit 1 IS set)");
        System.out.println("isBitSet(n, 0) = " + isBitSet(n, 0) + "  (bit 0 is NOT set)");
        System.out.println("setBit(n, 0)   = " + Integer.toBinaryString(setBit(n, 0)));
        System.out.println("clearBit(n, 1) = " + Integer.toBinaryString(clearBit(n, 1)));
        System.out.println("toggleBit(n, 0)= " + Integer.toBinaryString(toggleBit(n, 0)));

        System.out.println("\n=== Power of 2 check ===");
        for (int x : new int[]{1, 2, 3, 4, 15, 16, 1024, 1025}) {
            System.out.println(x + " is power of 2: " + isPowerOfTwo(x));
        }

        System.out.println("\n=== Counting set bits (Brian Kernighan) ===");
        System.out.println("countSetBits(0b10110110) = " + countSetBits(0b10110110) + " (expect 5)");

        System.out.println("\n=== Find the single non-repeating number via XOR ===");
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Single number: " + findSingleNumber(nums) + " (expect 4)");

        System.out.println("\n=== XOR swap (no temp variable) ===");
        int[] swapped = xorSwap(5, 9);
        System.out.println("swap(5, 9) -> a=" + swapped[0] + ", b=" + swapped[1]);

        System.out.println("\n=== Lowest set bit isolation ===");
        System.out.println("lowestSetBit(0b1011000) = " + Integer.toBinaryString(lowestSetBit(0b1011000)) + " (expect 1000)");
    }
}