/*
* Hieu Trung Nguyen
*/

/*
* This is a class representing a CES student with its
* till selection behavior.
*/
public class CESStudent implements Student {
    private String name;
    private MyQueue selectedTill;
    
    /*
    * Construct a new CES student with the given name.
    */
    public CESStudent(String name) {
        this.name = name;
        selectedTill = null;
    }
    
    /*
    * Select the first open till with less than 5 students
    * then return that till. Select first till if all open tills have
    * 5 students.
    */
    public MyQueue selectTill(CoffeeShop shop) {
        for (MyQueue till : shop.tillsList) {
            if (till.size() < 5 && !till.isEmpty()) {
                return till;
            }
        }
        selectedTill = shop.tillsList.get(0);
        return selectedTill;
    }
    
    /*
    * Return a string representation of this CES student.
    */
    public String toString() {
        return name;
    }
}
