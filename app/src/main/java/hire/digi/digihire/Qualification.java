package hire.digi.digihire;

public class Qualification{
    private String qualname;
    private String qual_id;

    public Qualification() {
    }

    public Qualification(String qualname, String qual_id) {
        this.qualname = qualname;
        this.qual_id = qual_id;
    }

    public String getQualname() {
        return qualname;
    }

    public void setQualname(String qualname) {
        this.qualname = qualname;
    }

    public String getQual_id() {
        return qual_id;
    }

    public void setQual_id(String qual_id) {
        this.qual_id = qual_id;
    }

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return qualname
     */
    @Override
    public String toString() {
        return qualname;
    }
}