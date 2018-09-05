package com.example.didi.Controller;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:19
*/

import com.example.didi.Service.ApprovalServiceImpl;
import com.example.didi.domain.entity.ApprovalEntity;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class Approval58Controller {
//    private Logger log =Logger.getLogger(Approval58Controller.class);
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Approval58Controller.class);

    @Resource
    private ApprovalServiceImpl approvalService;

    //查询信息
    @RequestMapping("/approval/org/search")
    @ResponseBody
    public List<ApprovalEntity> SearchApproval(HttpServletRequest request) throws Exception
    {
       String phone =request.getParameter("phone");
       List<ApprovalEntity> result =approvalService.SearchApproval(phone);
        LOGGER.info("入参为:"+"phone="+phone);
        LOGGER.info("参数返回："+result);

       return result;

    }


    //认证修改
    @RequestMapping("/approval/org/change")
    @ResponseBody
    public int UpdateApproval( HttpServletRequest request) {

        String custom =request.getParameter("custom");
        String approval_status=request.getParameter("approval_status");

//        Boolean Validate = ValidateUtils.Negative_integer(custom);
//        if (Validate == false){
//            return 0;
//        }
        LOGGER.info("商家id={}，状态={}",custom,approval_status);

        int result =approvalService.Approval_status(Long.valueOf(custom), Integer.parseInt(approval_status));

        return result;
    }

}
