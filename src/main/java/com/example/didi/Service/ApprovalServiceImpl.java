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

        return approval58Mapper.SearchApproval(phone);
    }


    //修改

    @Override
    public int Approval_status( Long custom, int approval_status ) {

        TcRespDto<ApprovalDto> tcRespDto = new TcRespDto<ApprovalDto>();

        if (ApprovalEnum.getApprovalStatusEnum(approval_status) == 0 ||
                 ApprovalEnum.getApprovalStatusEnum(approval_status)>=4) {
            tcRespDto.setErrorMessage("Status错误:code不存在 ：" + approval_status);
            return 0;
        }

        return approval58Mapper.Approval_status(custom,approval_status);
    }



}
