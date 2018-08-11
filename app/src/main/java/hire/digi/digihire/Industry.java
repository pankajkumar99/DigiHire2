package hire.digi.digihire;

public class Industry{
    private String industryname;
    private String industry_id;

    public Industry() {
    }

    public Industry(String industryname, String industry_id) {
        this.industryname = industryname;
        this.industry_id = industry_id;
    }

    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return industryname
     */
    @Override
    public String toString() {
        return industryname;
    }
}