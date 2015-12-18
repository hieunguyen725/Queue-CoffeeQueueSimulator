/*
* Hieu Trung Nguyen
*/

import java.util.*;

/*
* This is a Coffeeshop class representing the UWT-Starbuck.
* It contains 5 tills with one door queue, and serves students in the
* open tills every one minute while letting the students at door queue
* enter the open tills if there are any.
*/
public class CoffeeShop {
    protected List<MyQueue> tillsList;
    private MyQueue twoMinExit;
    private MyQueue oneMinExit;
    private MyQueue satisfiedCustomers;
    private MyQueue doorQueue;
    private int tillsOpening;
    private boolean isFull;
    
    /*
    * Construct an empty Starbucks.
    */
    public CoffeeShop() {
        tillsList = new ArrayList<MyQueue>();
        for (int i = 0; i < 5; i++) {
            tillsList.add(new MyQueue());
        }
        twoMinExit = new MyQueue();
        oneMinExit = new MyQueue();
        doorQueue = new MyQueue();
        satisfiedCustomers = new MyQueue();
        tillsOpening = 1;
        isFull = false;
    }
    
    /*
    * Add a new student to their selected till. If any till is too full,
    * the till is then split into another new till. If all five tills are full,
    * add the student into the door queue.
    */
    public void offer(Student student) {
        if (isFull) {
            doorQueue.offer(student);
        } else {
            MyQueue selectedTill = student.selectTill(this);
            selectedTill.offer(student);
            if (selectedTill.size() > 5 && tillsOpening < 5) {
                tillsList.set(tillsOpening, selectedTill.split());
                tillsOpening++;
            }
        }
        updateAvailability();
    }
    
    /*
    * Serve every student at a till, deliver refreshments to those in exit queue
    * and letting as many students from door queue into the shop as possible.
    * Then return a MyQueue of the satisfied customers with their refreshments at
    * the minute.
    */
    public MyQueue poll() {
        while (!satisfiedCustomers.isEmpty()) {
            satisfiedCustomers.poll();
        }
        while (!oneMinExit.isEmpty()) {
            satisfiedCustomers.offer(oneMinExit.poll());
        }
        while (!twoMinExit.isEmpty()) {
            oneMinExit.offer(twoMinExit.poll());
        }
        for (MyQueue till : tillsList) {
            if (till.size() > 0) {
                twoMinExit.offer(till.poll());
                if (!doorQueue.isEmpty()) {
                    till.offer(doorQueue.poll());
                }
            }
        }
        updateAvailability();
        return satisfiedCustomers;
    }
    
    /*
    * Helper method to update the number of open tills and whether the shop is
    * full or not.
    */
    private void updateAvailability() {
        isFull = true;
        tillsOpening = 5;
        for (MyQueue till : tillsList) {
            if (till.size() < 5) {
                isFull = false;
                if (till.isEmpty() && tillsOpening > 1) {
                    tillsOpening--;
                }
            }
        }
    }
    
    /*
    * Display contents of the Starbucks's tills, its door queue and the
    * exit queues.
    */
    public void displayShop() {
        System.out.println("Door Queue: " + doorQueue);
        int i = 0;
        for (MyQueue till : tillsList) {
            System.out.println("Till " + i + ": " + till.toString());
            i++;
        }
        System.out.println("Exit Queue 2 min: " + twoMinExit);
        System.out.println("Exit Queue 1 min: " + oneMinExit);
    }
}
