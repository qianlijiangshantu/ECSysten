package Ansin.web.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ansin.web.bean.BriefingInfoTblBean;
import Ansin.web.entity.BriefingInfoTblEntity;
import Ansin.web.mapper.BriefingTblMapper;
import Ansin.web.service.B0601Service;
import Ansin.web.util.EditUtil;
import Ansin.web.vueForm.B0601VueForm;

@Service
public class IB0601ServiceImpl implements B0601Service {

    @Autowired
    private BriefingTblMapper briefingTblMapper;

    /**
     * 説明会情報表示
     * 
     * @return List<BriefingApplTblEntity>
     */
    @Override
    public List<BriefingInfoTblBean> getBriefingInfoEntities(B0601VueForm b0601VueForm) {
    	List<BriefingInfoTblEntity> briefingInfoTblEntity = briefingTblMapper.getBriefingInfoList(b0601VueForm);
    	List<BriefingInfoTblBean> briefingInfoTblBean = new ArrayList<BriefingInfoTblBean>();
    	for (BriefingInfoTblEntity BriefingInfo : briefingInfoTblEntity) {
    		BriefingInfoTblBean Bean = new BriefingInfoTblBean();
    		BeanUtils.copyProperties(BriefingInfo, Bean);
    		Bean.setBriefingId(EditUtil.intUtil(BriefingInfo.getBriefingId()));
    		Bean.setCompanyId(EditUtil.intUtil(BriefingInfo.getCompanyId()));
    		Bean.setBriefingDate(EditUtil.dateUtil(BriefingInfo.getBriefingDate()));
    		Bean.setDateFrom(EditUtil.timeUtil(BriefingInfo.getDateFrom()));
    		Bean.setDateTo(EditUtil.timeUtil(BriefingInfo.getDateTo()));
    		Bean.setProCntPlan(EditUtil.intUtil(BriefingInfo.getProCntPlan()));
    		briefingInfoTblBean.add(Bean);
		}
    	
        return briefingInfoTblBean;
    }

    /**
     * 説明会情報を削除
     * 
     * @return int
     */
    @Override
    public int delBriefingInfoEntities(List<Integer> BriefingIds, Integer companyId, Integer userCd) {

        return briefingTblMapper.briefingInfoDelete(BriefingIds, companyId, userCd);

    }

}
