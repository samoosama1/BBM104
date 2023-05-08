public class Academic extends Member {
    public Academic(int ID) {
        super(ID, 4, 14);
    }

    public String toString() {
        return String.format("Academic [id: %d]", getID());
    }
}

