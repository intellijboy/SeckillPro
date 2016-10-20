import com.jxufe.enums.SeckillStatEnum;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by liuburu on 2016/10/14.
 */
public class TestProject {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void sayHello() {
        try{
            int i=10/0;
        }catch (Exception e){
            logger.error("数学异常："+e.getMessage(),e);
        }
    }

    @Test
    public void testEnums() {
        System.out.println(SeckillStatEnum.stateOf(-2));
    }
}
