/*
* Hieu Trung Nguyen
*/

import java.util.List;

/*
* This is a class representing a CSS student with its
* till selection behavior.
*/
public class CSSStudent implements Student {
    private String name;
    private MyQueue selectedTill;
    
    /*
    * Construct a new CSS student with the given name.
    */
    public CSSStudent(String name) {
        this.name = name;
        selectedTill = null;
    }
    
    /*
    * Select the first open till with the fewest student then return
    * that till.
    */
    public MyQueue selectTill(CoffeeShop shop) {
        List<MyQueue> currentTills = shop.tillsList;
        int min = Integer.MAX_VALUE;
        int shortestTill = 0;
        for (int i = currentTills.size() - 1; i >= 0; i--) {
            if (currentTills.get(i).size() <= min && !currentTills.get(i).isEmpty()) {
                min = currentTills.get(i).size();
                shortestTill = i;
            }
        }
        selectedTill = currentTills.get(shortestTill);
        return selectedTill;
    }
    
    /*
    * Return a string representation of this CSS student.
    */
    public String toString() {
        return name;
    }
}
