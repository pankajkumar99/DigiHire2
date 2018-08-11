package hire.digi.digihire;

public class Area{
    private String areaname;
    private String area_id;

    public Area() {
    }

    public Area(String areaname, String area_id) {
        this.areaname = areaname;
        this.area_id = area_id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return degreename
     */
    @Override
    public String toString() {
        return areaname;
    }
}