/*
* Hieu Trung Nguyen
*/

/*
* This is MyQueue class where it stores Students as data.
* It adds the data through the back and remove it to the wrong.
* MyQueue also let the user split the queue and create another queue
* from the split.
*/
public class MyQueue {
    private QueueNode front;
    private QueueNode back;
    private int size;
    
    /*
    * Construct an empty queue.
    */
    public MyQueue() {
        front = null;
        back = null;
        size = 0;
    }
    
    /*
    * Return whether the queue is empty.
    */
    public boolean isEmpty() {
        return size <= 0;
    }
    
    /*
    * Add a new student into the back of the queue.
    */
    public void offer(Student student) {
        QueueNode newNode = new QueueNode(student);
        if (size == 0) {
            front = newNode;
            back = front;
        } else {
            back.next = newNode;
            back = back.next;
        }
        size++;
    }
    
    /*
    * Remove and return the current student from the front of the queue.
    */
    public Student poll() {
        Student currentStudent = front.student;
        if (front.next != null) {
            front = front.next;
        } else {
            front = null;
        }
        size--;
        return currentStudent;
    }
    
    /*
    * Return the first student in the queue without changing the queue.
    * Return null if the queue is empty.
    */
    public Student peek() {
        if (front != null) {
            return front.student;
        } else {
            return null;
        }
    }
    
    /*
    * Split the queue into two, between odd and even position, the original
    * queue keeping the students lining in odd positions while returning
    * the new queue with students in even positions.
    */
    public MyQueue split() {
        MyQueue newQueue = new MyQueue();
        int n = size;
        for (int i = 1; i <= n; i++) {
            Student currentStudent = this.poll();
            if (i % 2 == 0) {
                newQueue.offer(currentStudent);
            } else {
                this.offer(currentStudent);
            }
        }
        return newQueue;
    }
    
    /*
    * Return the number of students in the queue.
    */
    public int size() {
        return size;
    }
    
    /*
    * Return a string representation of the queue.
    */
    public String toString() {
        if (front != null) {
            String queueDisplay = "[" + front.student;
            // Giving a big thanks to my previous CSE 143 course website for this for loop layout.
            // Link to the website is included at the end of this class.
            for (QueueNode current = front.next; current != null; current = current.next) {
                queueDisplay = queueDisplay + ", " + current.student;
            }
            return queueDisplay + "]";
        } else {
            return "[]";
        }
    }
    
    /*
    * Inner QueueNode class, keeping student as the data type
    * and a reference to the next node.
    */
    private class QueueNode {
        private Student student;
        private QueueNode next;
        
        public QueueNode(Student student) {
            this.student = student;
            this.next = null;
        }
    }
}

// http://courses.cs.washington.edu/courses/cse143/14wi/notes/notes07.html