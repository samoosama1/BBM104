public class Student extends Member {
    public Student(int ID) {
        super(ID, 2, 7);
    }

    public String toString() {
        return String.format("Student [id: %d]", getID());
    }
}
