/*
* Hieu Trung Nguyen
*/

import java.util.List;

/*
* This is a class representing a ITS student with its
* till selection behavior.
*/
public class ITSStudent implements Student {
    private String name;
    private MyQueue selectedTill;
    
    /*
    * Construct a new ITS student with the given name.
    */
    public ITSStudent(String name) {
        this.name = name;
        selectedTill = null;
    }
    
    /*
    * Select the first open till with the longest line that does
    * not have 5 students then return the selected till. Return
    * the first open till if all open tills have 5 students.
    */
    public MyQueue selectTill(CoffeeShop shop) {
        List<MyQueue> currentTills = shop.tillsList;
        int max = Integer.MIN_VALUE;
        int longestTill = 0;
        for (int i = currentTills.size() - 1; i >= 0; i--) {
            if (currentTills.get(i).size() >= max &&
                    (!currentTills.get(i).isEmpty() && currentTills.get(i).size() != 5)) {
                max = currentTills.get(i).size();
                longestTill = i;
            }
        }
        selectedTill = currentTills.get(longestTill);
        return selectedTill;
    }
    
    /*
    * Return a string representation of this ITS student.
    */
    public String toString() {
        return name;
    }
}
