package concertApplication;

/**
 * Created by Ewurafua on 2/23/2017.
 *
 * Class Description: The Date class is a model class that represents date objects.
 */

public class Date {

    int month;
    int day;
    int time;
    int year;
    private int ID;

    public Date(int m, int d, int t, int y)
    {
        this.month = m;
        this.day = d;
        this.time = t;
        this.year = y;
        ID++;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }


    public String getDate(int m, int d, int t, int y)
    {
        return m+"/"+d+"/"+y+" at :"+t;
    }
}
