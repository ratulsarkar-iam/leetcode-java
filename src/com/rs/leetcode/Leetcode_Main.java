package com.rs.leetcode;

import com.rs.leetcode.easy.Leetcode_9;
import com.rs.leetcode.med.Leetcode_151;
import com.rs.leetcode.med.Leetcode_74;

public class Leetcode_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Leetcode_204 leetcode_204= new Leetcode_204();
		Leetcode_74 leet = new Leetcode_74 ();

		System.out.println(Integer.MAX_VALUE);
		int[][] matrix={{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		System.out.println(leet.searchMatrix (matrix,34));

	}

}
