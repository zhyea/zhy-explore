package org.chobit.spring.bf;

import org.apache.commons.codec.digest.MurmurHash3;

public class BloomFilterHelper {


    private final int bitSize;

    private final int numHashFunctions;

    public BloomFilterHelper(int expectedInsertions, double fpp) {
        // 计算bit数组长度
        bitSize = optimalNumOfBits(expectedInsertions, fpp);
        // 计算hash方法执行次数
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }

    /**
     * 计算offset
     */
    public int[] murmurHashOffset(String value) {
        int[] offset = new int[numHashFunctions];

        long hash64 = hash64(value);
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            offset[i - 1] = nextHash % bitSize;
        }

        return offset;
    }

    /**
     * 计算bit数组长度
     */
    private int optimalNumOfBits(long n, double p) {
        if (p == 0) {
            // 设定最小期望长度
            p = Double.MIN_VALUE;
        }
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 计算hash方法执行次数
     */
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    /**
     * 执行hash64运算
     *
     * @param value 值
     * @return hash计算结果
     */
    private long hash64(String value) {
        long[] result = MurmurHash3.hash128x64(value.getBytes());
        return result[0];
    }

}
