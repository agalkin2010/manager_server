public class ResultTotal {
    private ResultCategory maxCategory;

    public ResultTotal(ResultCategory maxCategory) {
        this.maxCategory = maxCategory;
    }
}

//{
//        "maxCategory": {
//        "category": "еда",
//        "sum": 350000
//        },
//        "maxYearCategory": {
//        "category": "еда",
//        "sum": 300000
//        },
//        "maxMonthCategory": {
//        "category": "еда",
//        "sum": 24000
//        },
//        "maxDayCategory": {
//        "category": "одежда",
//        "sum": 3000
//        },
//        }

class ResultCategory{

    private String category;
    private long sum;

    public ResultCategory(String category, long sum) {
        this.category = category;
        this.sum = sum;
    }


}
