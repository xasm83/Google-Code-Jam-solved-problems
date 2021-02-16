package leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;


public class Rotate {
    public void rotate(int[] array, int times){
        //[1 2^ 3 4 5]  [5 1 2 3 4] [4 5 1 2 3]
        int rotationPoint = times % array.length - 1;
        int[] res = new int[array.length ];
        System.arraycopy(array,rotationPoint,res,0,array.length - rotationPoint);
        System.out.println(Arrays.toString(res));
    }
}
