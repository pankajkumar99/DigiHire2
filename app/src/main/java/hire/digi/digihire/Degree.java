package hire.digi.digihire;

public class Degree{
    private String degreename;
    private String degree_id;

    public Degree() {
    }

    public Degree(String degreename, String degree_id) {
        this.degreename = degreename;
        this.degree_id = degree_id;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }

    public String getDegree_id() {
        return degree_id;
    }

    public void setDegree_id(String degree_id) {
        this.degree_id = degree_id;
    }

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return degreename
     */
    @Override
    public String toString() {
        return degreename;
    }
}