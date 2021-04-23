import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett
 * @title: LetterCombinations
 * @description: TODO 电话号码中的字母组合，
 * @date 2021/4/23 14:30
 */
public class LetterCombinations {

    public static void main(String[] args) {

    }
    public List<String> letterCombination(String digits){
        List<String>   combinations= new ArrayList<String>();
        if(digits.length()==0)
            return combinations;

        Map<Character,String> phonemap= new HashMap<Character,String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        backtrack(combinations,phonemap,digits,0,new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations,Map<Character,String> phoneMap,
                          String digits,int index,StringBuffer combination){
        if(index==digits.length()){
            combinations.add(combination.toString());
        }else{
            char digit=digits.charAt(index);
            String letters=phoneMap.get(digit);
            int lettersCount=letters.length();

            for(int i=0;i<lettersCount;i++){
                combination.append(letters.charAt(i));
                backtrack(combinations,phoneMap,digits,index+1,combination);
                combination.deleteCharAt(index);
            }
        }

    }

}
