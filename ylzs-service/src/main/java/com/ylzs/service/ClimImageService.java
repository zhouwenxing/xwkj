package com.ylzs.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylzs.core.mapper.ClimImageMapper;
import com.ylzs.core.model.ClimImage;
import com.ylzs.util.CommonUtil;
import com.ylzs.util.DateTimeUtil;

@Service
public class ClimImageService {
	
	@Autowired
	private ClimImageMapper climImageMapper;
	
	 /**
     * 批量更新图片
     * @param dynamicId - 动态id
     * @param picIdList - 图片ids
     */
	@Transactional
	void batchUpdateImage(String dynamicId, List<String> picIdList){
		ClimImage climImage = new ClimImage();
		climImage.setDynamicId(dynamicId);
		
		Timestamp nowTime = DateTimeUtil.getNowTimestamp();
		for(int i=0;i<picIdList.size();i++){
			String picId = picIdList.get(i);
			climImage.setId(CommonUtil.uuid());
			climImage.setImageType(1);
			climImage.setImageSort(i + 1);
			climImage.setImageId(picId);
			climImage.setCreateTime(nowTime);
			if(i > 0){
				climImage.setImageType(2);
			}
			climImageMapper.insert(climImage);
		}
	}

}
