import java.util.*;

/** 
 * O(N*w) where N: number of elements, W:widest element.
 *
 * buckets[] = List[0,...,9];
 * for(pow = 10,100,1000,....) {
 *  for x in array: bucket[ x % pow ].add(x)
 *
 *  Dump buckets in order into array
 * }
 */
public class Radix {
  public static int[] lsd(final int[] array) {
    final List<Integer>[] map = new List[10];

    for(int i=0; i<map.length; i++) map[i] = new ArrayList<>();

    boolean moreDigits = true;
    for(int k=0; moreDigits; k++) {
      final int pow100 = (int) Math.pow(10, k+1);
      final int pow10 = (int) Math.pow(10, k);
      // Classify all elements into arrays
      moreDigits = false;
      for(int i=0; i<array.length; i++) {
        if(pow10<array[i]+1) moreDigits = true;
        map[(array[i] % pow100)/pow10].add(array[i]);
      }

      if(moreDigits) {
        for(int i=0, l=0; i<map.length; i++) {
          for(int j=0; j<map[i].size(); j++,l++) {
            array[l] = map[i].get(j);
          }
          map[i].clear();
        }
      }
    }
    return array;
  }
  


  public static void main(final String args[]) {
    final int[] arr = new int[] { 1,10,100,99,9,89,74,3, 100};
    lsd(arr);

    for(int i=0; i<arr.length; i++) System.out.print(arr[i]+" ");
    System.out.println();
  }
}
