package leetcode;

//https://leetcode.com/problems/reverse-bits/submissions/
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result = result << 1;
            int temp = n & 1;
            result = (result | temp);
            n = n >> 1;
        }
        return result;
    }

//    golang comand and conquerer
//    func reverseBits(num uint32) uint32 {
//        num = (num >> 16) | (num << 16)
//        num = ((num & 0xff00ff00) >> 8) | ((num & 0x00ff00ff) << 8)
//        num = ((num & 0xf0f0f0f0) >> 4) | ((num & 0x0f0f0f0f) << 4)
//        num = ((num & 0xcccccccc) >> 2) | ((num & 0x33333333) << 2)
//        num = ((num & 0xaaaaaaaa) >> 1) | ((num & 0x55555555) << 1)
//        return num
//    }
}
