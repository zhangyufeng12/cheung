package com.example.didi.Service;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:33
*/

import com.example.didi.Service.ServiceImpl.ApprovalService;
import com.example.didi.domain.entity.ApprovalEntity;
import com.example.didi.domain.mapper.Approval58Mapper;
import com.example.didi.dto.ApprovalDto;
import com.example.didi.dto.TcRespDto;
import com.example.didi.enums.ApprovalEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ApprovalService")
public class ApprovalServiceImpl implements ApprovalService{


    private Logger log= Logger.getLogger(ApprovalServiceImpl.class);
    @Resource
    private Approval58Mapper approval58Mapper;


    //查询
    @Override
    public List<ApprovalEntity> SearchApproval( String phone ) {
        if (phone==""||phone==null){
            return null;
        }
        return approval58Mapper.SearchApproval(phone);
    }


    //修改

    @Override
    public String Approval_status( String phone, int approval_status ) {

        TcRespDto<ApprovalDto> tcRespDto = new TcRespDto<ApprovalDto>();

        //判断所要修改数据是否存在
        if (SearchApproval( phone ).size() == 0) {
            return ApprovalEnum.NON.getMsg();
        }

        //合法状态范围 1-3
        if (ApprovalEnum.getApprovalStatusEnum(approval_status) == 0 ||
                ApprovalEnum.getApprovalStatusEnum(approval_status)>=4) {
            tcRespDto.setErrorMessage("Status错误:code不存在 ：" + approval_status);
            return ApprovalEnum.EXCE.getMsg();
        }

        Long custom = SearchApproval(phone).get(0).getCustomId();

        //获取custom不能为空
        if (custom==null){
            return ApprovalEnum.NULL.getMsg();
        }

        int approval = approval58Mapper.Approval_status( custom, approval_status );
        //返回更改状态
        if (approval != 0 && approval_status == 1) {
            return ApprovalEnum.WAIT.getMsg();
        }
        if (approval != 0 && approval_status == 2) {
            return ApprovalEnum.SUCCESS.getMsg();
        }
        if (approval != 0 && approval_status == 3) {
            return ApprovalEnum.LOSE.getMsg();
        }

        return ApprovalEnum.FAIL.getMsg();
    }


}
