package com.jxufe.web;

import com.jxufe.dto.Exposer;
import com.jxufe.dto.SeckillExecution;
import com.jxufe.dto.SeckillResult;
import com.jxufe.entity.Seckill;
import com.jxufe.enums.SeckillStatEnum;
import com.jxufe.exception.RepeatKillException;
import com.jxufe.exception.StoreEmptyException;
import com.jxufe.exception.TimeOverException;
import com.jxufe.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuburu on 2016/10/18.
 */
@Controller
@RequestMapping("seckill")
public class SeckillControl{

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/index")
    public String toListPage(Map map){
        List<Seckill> seckills= seckillService.getSeckillList();
        map.put("seckills",seckills);
        return "list";
    }

    @RequestMapping("/{seckillId}/detail")
    public String toDetailPage(@PathVariable("seckillId") Integer seckillId, Model model){
        Seckill seckill = seckillService.getById(seckillId);
        model.addAttribute("seckillDetail",seckill);
        return "detail";
    }

    @RequestMapping("/now")
    @ResponseBody
    public SeckillResult<Date> getNowTime(){
        SeckillResult<Date> nowTime = new SeckillResult<Date>(true,new Date());
        return nowTime;
    }

    @RequestMapping("/{seckillId}/exposer")
    @ResponseBody
    public Object exposeUrl(@PathVariable("seckillId") Integer seckillId){
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            SeckillResult<Exposer> seckillResult = new SeckillResult<Exposer>(true,exposer);
            return seckillResult;
        } catch (TimeOverException e) {
            return new SeckillResult<String>(false,SeckillStatEnum.TIME_OVER_EXCEPTION.getStateInfo());
        }catch (Exception e) {
            return new SeckillResult<String>(false,SeckillStatEnum.INNER_ERROR.getStateInfo());
        }
    }

    @RequestMapping("/{seckillId}/{md5}/execution")
    @ResponseBody
    public Object excuteSeckillAction(
            @PathVariable("seckillId") Integer seckillId,
            @PathVariable("md5") String md5,
            @CookieValue("userPhone") Long userPhone
    ){
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        } catch (RepeatKillException e) {
            return new SeckillResult<String>(false,SeckillStatEnum.REPEAT_EXCEPTION.getStateInfo());
        }catch (TimeOverException e) {
            return new SeckillResult<String>(false,SeckillStatEnum.TIME_OVER_EXCEPTION.getStateInfo());
        }catch (StoreEmptyException e) {
            return new SeckillResult<String>(false,SeckillStatEnum.STORE_EMPTY_EXCEPTION.getStateInfo());
        }catch (Exception e) {
            return new SeckillResult<String>(false,SeckillStatEnum.INNER_ERROR.getStateInfo());
        }
    }
}
