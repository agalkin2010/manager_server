package stat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


public class Statistic {

    private List<Category> categories;
    private List<String> categ = new ArrayList<>();
    private List<Long> totalAmount = new ArrayList<>();
    private List<Information> inform;

    public Statistic(List<Category> categories, List<Information> inform) {
        this.categories = categories;
        this.inform = inform;

        for(Category c : categories){
            if (!categ.contains(c.category)){
                categ.add(c.category);
                totalAmount.add(0l);                              }
        }
        categ.add("Other");
        totalAmount.add(0l);
    }

    private void calcTotalAmount(Information inf){
        int k = -1;
        for (int i=0; i < categories.size(); i++) {
            if (categories.get(i).name.equals(inf.getTitle())) {
                k = categ.indexOf(categories.get(i).category);
                totalAmount.set(k, inf.getSum() + totalAmount.get(k));
                break;
            }
        }
        if (k == - 1) {
            k = categ.indexOf("Other");
            totalAmount.set(k, inf.getSum()+totalAmount.get(k));
        }
    }

    public void addPosition(Information inf){
        inform.add(inf);

        calcTotalAmount(inf);

    }

    public int getMaxCategory() {

        long maxAmount = -1;
        int maxAmountNum = 0;
        for (int i = 0; i < totalAmount.size(); i++) {
            if (totalAmount.get(i) > maxAmount){
                maxAmount = totalAmount.get(i);
                maxAmountNum = i;
            }
        }

        return maxAmountNum;

    }

    public long getMaxCategoryValue(){
        int i = getMaxCategory();
        return totalAmount.get(i);
    }

    public String getJsonStatistic(){
        int num = getMaxCategory();
        ResultCategory max = new ResultCategory(categ.get(num), totalAmount.get(num));
        ResultTotal res = new ResultTotal(max);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(res);
    }

}




