package com.jiale.test.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

public class GuavaBloomFilter {
    public static void main(String[] args) {
        // 初始化布隆过滤器，设计预计元素数量为100_0000L，误差率为1%
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 100_0000, 0.001);
        int n = 1000000;
        for (int i = 0; i < n; i++) {
            bloomFilter.put(String.valueOf(i));
        }
        int count = 0;
        for (int i = 0; i < (n * 2); i++) {
            if (bloomFilter.mightContain(String.valueOf(i))) {
                count++;
            }
        }
        System.out.println("过滤器误判率：" + 1.0 * (count - n) / n);
    }

}
