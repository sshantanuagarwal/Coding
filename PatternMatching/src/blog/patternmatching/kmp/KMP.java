package blog.patternmatching.kmp;
import java.util.Arrays;
/**
 * Knuth- Morris Pratt Algorithm
 * @author PRATEEK
 */
public class KMP {

	/**
	 * Pattern processing before KMP, prefix function calculates the longest
	 * prefix which is a longest suffix in the pattern
	 */
	private static int[] patternProcessing(String pat) {
		int size = pat.length();
		int[] table = new int[size];

		int i = 1, j = 0;
		while (i < size) {
			if (pat.charAt(i) == pat.charAt(j))
					table[i++] = ++j;

			//Mismatch
			else if (j > 0)
				j = table[j - 1];

			//Mismatch
			else //j==0
				table[i++] = 0;
				
		}
		System.out.println("Pattern Processing Table : " + Arrays.toString(table));
		return table;
	}

	
	
	/**
	 * KMP Sub-routine 
	 * @param text: input text
	 * @param pat input pattern
	 * @return : index at which pattern is found in text
	 */
	public static int kmp(String text, String pat) {
		int patLen = pat.length();
		int textLen = text.length();
		String a;
		int i = 0, j = -1;
		int[] table = patternProcessing(pat);

		int count =0;
		while (i < textLen) 
		{
			count++;
			if (text.charAt(i) == pat.charAt(j+1)) 
			{
				if (j+1 == patLen-1)
				{
					
					System.out.println("Found : -->" + (i -j));
					i++;
					return i - j;
				}
					
				else {
					i++;
					j++;
				}

			} 
			else if(j>0)
				j = table[j];
			else
			{
				j = -1;
				i++;
			}
				 
				
		}
		//System.out.println("Count :" +count);
		//System.out.println(text.length());
		return -1;
	}
	
	public static void main(String[] args) {
		//String text = "ababaabcbaababa";
		//String pat = "ababa";

		String text = "ABABCABCABABABD";
		String pat = "ABABD";

		
		int index= kmp(text,pat);
		System.out.println("found at " + index);
		
	}
}
