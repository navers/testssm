package test.util;

import com.util.DateUtil;
import org.junit.Test;

import java.util.Date;

public class TestDate {

    @Test
    public void testDate(){
        String str = "1993年04月05日";
        Date date  = DateUtil.string2date(str);
        System.out.println(date);
        Date date1 = new Date();
        String s = DateUtil.date2string(date1);
        System.out.println(s);
    }
}
