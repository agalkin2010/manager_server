package stat;

public class Information {

    private String title;
    private String date;
    private long sum;

    public Information(String title, String date, long sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public long getSum() {
        return sum;
    }
}
