package com.example.didi.Controller;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:19
*/

import com.alibaba.fastjson.JSONObject;
import com.example.didi.Service.ApprovalService;
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
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger( Approval58Controller.class );

    @Resource
    private ApprovalService approvalService;

    //查询信息
    @RequestMapping("/approval/org/search")
    @ResponseBody
    public List<ApprovalEntity> SearchApproval( HttpServletRequest request ) throws Exception {
        String phone = request.getParameter( "phone" );
        LOGGER.info( "入参为:" + "phone=" + phone );

        List<ApprovalEntity> result = approvalService.SearchApproval( phone );
        LOGGER.info( "参数返回：" + result );

        return result;

    }

    //认证修改
    @RequestMapping("/approval/org/change")
    @ResponseBody
    public JSONObject UpdateApproval( HttpServletRequest request ) {

        String phone = request.getParameter( "phone" );
        String approval_status = request.getParameter( "approval_status" );
        LOGGER.info( "商家手机号={}，状态={}", phone, approval_status );

        JSONObject result = new JSONObject();
        result.put( "result", approvalService.Approval_status( phone, Integer.parseInt( approval_status ) ) );
        LOGGER.info( "返回結果：" + result );

        return result;
    }

}
