import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stat.Category;
import stat.Information;
import stat.Statistic;

import java.util.ArrayList;
import java.util.List;

public class StatisticTest {

    @Test
    void testStat(){
        List<Category> listCat = new ArrayList<>();
        List<Information> listInf = new ArrayList<>();

        Category cat = new Category();
        cat.name = "булка";
        cat.category = "еда";
        listCat.add(cat);

        Statistic statistic = new Statistic(listCat, listInf);

        Information inform = new Information("булка", "2022.02.01", 300l);
        statistic.addPosition(inform);

        long expect = 300l;
        long current = statistic.getMaxCategoryValue();

        Assertions.assertEquals(expect, current);

    }

}
