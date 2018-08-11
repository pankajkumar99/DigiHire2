package hire.digi.digihire;

public class City{
    private String cityname;
    private String city_id;

    public City() {
    }

    public City(String cityname, String city_id) {
        this.cityname = cityname;
        this.city_id = city_id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return cityname
     */
    @Override
    public String toString() {
        return cityname;
    }
}