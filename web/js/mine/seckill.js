/**
 * Created by liuburu on 2016/10/18.
 */
var seckill = {
    URL: {
        exposerUrl: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        excuteSeckillUrl:function (seckillId, md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    checkUserCookie: function (data) {
    //    console.log("checkUserCookie method is working......");
        var userPheone = $.cookie("userPhone");
        if (userPheone == undefined) {//显示登陆弹出层，绑定登陆事件
            $('#killPhoneModal').modal({
                show: true,
                keyboard: false,
                backdrop: 'static'
            });
            $('#killPhoneModal').on('shown.bs.modal', function () {//模态框显示完毕后，进行登陆事件绑定
   //             console.log("modal has shown...");
                seckill.bindLoginBtnEvent();
                //TODO 判断逻辑（1.计时逻辑 2.秒杀业务已经ing完成逻辑 3.重福秒杀逻辑等..）
                seckill.judgeSeckillTime(data);
            })
        } else {
            seckill.judgeSeckillTime(data);
        }
    },
    judgeSeckillTime: function (seckillObj) {//判断逻辑（1.计时逻辑 2.秒杀业务已经ing完成逻辑 3.重福秒杀逻辑等..）
        $.post(seckill.URL.exposerUrl(seckillObj.seckillId), {}, function (data) {
            if (data.success == true) {//获取到秒杀地址,进入倒计时，倒计时完成计入秒杀
                if (data.data.exposed == false) {
                    var startTime = new Date(data.data.start + 500);
                    var $seckillBox = $('#seckillBox');
                    $seckillBox.countdown(startTime, function (e) {
                        var format = e.strftime('秒杀计时: %D天 %H时 %M分 %S秒');
                        $seckillBox.html(format);
                    }).on('finish.countdown', function () {
                        //  seckill.handlerSeckillKill(seckillId, $seckillBox);
                        console.log("计时动作完成");
                        $seckillBox.html('<h3>活动进行中...</h3>');
                        $('#killBeginBtn').removeClass('disabled');
                        //倒计时完成后添加秒杀按钮事件
                        $.post(seckill.URL.exposerUrl(seckillObj.seckillId), {},function (data) {
                            seckill.bindSeckillActionEvent(data.data.seckillId,data.data.md5);
                        })
                    });
                }else{
                    $('#killBeginBtn').removeClass('disabled');
                    //产品正在活动期间，可以进入倒计时
                    $('#seckillBox').html('<h3>活动进行中...</h3>');
                    seckill.bindSeckillActionEvent(data.data.seckillId,data.data.md5);
                }
            } else {//秒杀时间未开始或者已经结束了
                $('#killBeginBtn').addClass('disabled');
                $('#killBeginBtn').html(data.data);
            }
        })
    },
    bindSeckillActionEvent:function (seckillId,md5) {
        $('#killBeginBtn').one("click",function () {
            $(this).addClass('disabled');
            var excuteUrl = seckill.URL.excuteSeckillUrl(seckillId,md5);
             $.post(excuteUrl,{},function (data) {
                 if(data.success==true){
                     $('#killBeginBtn').html("秒杀成功");
                 }else{
                     $('#killBeginBtn').html(data.data);
                 }
             })
        });
    },
    bindLoginBtnEvent: function () {
    //    console.log("login btn click");
        $('#loginBtn').bind("click", function () {//检验手机格式
            var phoneReg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
            var userPhone = $('#userPhone').val();
            if (phoneReg.test(userPhone)) {//1.正确则写入Cookie 隐藏模态框
          //      console.log("手机号正确");
                $('#killPhoneModal').modal('hide');
                $.cookie("userPhone", userPhone, {path: "/seckill", expires: 7});
            } else {//2.不正确则进行提示
                $('#phoneTipMsg').hide().html("手机格式不正确").show(500);
            }
        })
    }
}