package com.ylzs.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param mounClimId - 登山行程id
     * @param picIdList - 图片ids
     */
	void batchUpdateImage(String mounClimId, List<String> picIdList){
		ClimImage climImage = new ClimImage();
		climImage.setMounClimId(mounClimId);
		
		Timestamp nowTime = DateTimeUtil.getNowTimestamp();
		for(int i=0;i<picIdList.size();i++){
			String picId = picIdList.get(i);
			if(i == 0){
				climImage.setId(CommonUtil.uuid());
				climImage.setImageType(1);
				climImage.setImageSort(i + 1);
				climImage.setImageId(picId);
				climImage.setCreateTime(nowTime);
				climImageMapper.insert(climImage);
			}
		}
	}

}
