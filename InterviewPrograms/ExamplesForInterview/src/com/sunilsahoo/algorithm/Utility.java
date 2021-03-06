package com.sunilsahoo.algorithm;

public class Utility {
	public static String join(final String delimiter, final int[] arr){
        if(arr == null){
            return "";
        }
        StringBuilder productIdsWithQuantities = new StringBuilder();
        int startIndex = 0;
        int endIndex = arr.length;
        for(int counter = startIndex; counter < endIndex ; counter++){
            if(counter > startIndex){
                productIdsWithQuantities.append(delimiter);
            }
            productIdsWithQuantities.append(arr[counter]);
        }
        return productIdsWithQuantities.toString();
    }
}
