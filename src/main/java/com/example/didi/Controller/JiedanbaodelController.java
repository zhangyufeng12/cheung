package com.example.didi.Controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.didi.Service.JiedanbaodelService;
import com.example.didi.domain.entity.PolicyEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*********
 *
 * zhangyufeng
 * 2017.10.15
 * test
 *
 */

@Controller
@RequestMapping("/")
public class JiedanbaodelController {
    @Resource
    private JiedanbaodelService jiedanbaodelService;

    @RequestMapping("/jiedanbao/del")
    @ResponseBody
    public JSONObject testReq(HttpServletRequest request) throws JSONException {
        //获取mid
        String mid = request.getParameter("mid");
//            String aaa = Integer.toString(userService.delorder(phone));
        //打印
        JSONObject result1 = new JSONObject();
        JSONObject result = new JSONObject();
        if (mid==""){
            result.put("result","mid不能为空");
        } else {
            result1.put("subOrder", jiedanbaodelService.delorder(mid));
            result1.put("PayOrder", jiedanbaodelService.delpay(mid));
            result1.put("Policy", jiedanbaodelService.delpolicy(mid));
            result1.put("Detail", jiedanbaodelService.deldetail(mid));
            result1.put("PlanLog", jiedanbaodelService.dellog(mid));
            result1.put("DriverGroup", jiedanbaodelService.delgroup(mid));
            result1.put("DriverPlan", jiedanbaodelService.delplan(mid));
            result1.put("InitAmount", jiedanbaodelService.delamount(mid));
            result1.put("Product",jiedanbaodelService.delproduct(mid));

            result.put("result", result1.toJSONString());
        }
//            model.addAttribute("mid", user);
//            System.out.println(user);
        return result;
    }

    @RequestMapping("/jiedanbao/query")
    @ResponseBody
    /*
        JSONObject方法编写
        2018.1.10
     */
//    public JSONObject search(HttpServletRequest request) throws Exception {
//            String mid = request.getParameter("mid");
//            JSONObject req = null;
//            JSONObject result = new JSONObject();
//              List<Map<String,String>> search = jiedanbaodelService.Querypolicy(mid);
//              result.put("policy",search);
//
//              return result;
//    }


    public List<PolicyEntity> QueryPolicy(HttpServletRequest request) throws Exception{
        String mid = request.getParameter("mid");
        List<PolicyEntity> result =jiedanbaodelService.QueryPolicy(mid);
//        if(result.size() ==0){
//            result=null;
//        }else
//            return result;
        return result;
    }
}
