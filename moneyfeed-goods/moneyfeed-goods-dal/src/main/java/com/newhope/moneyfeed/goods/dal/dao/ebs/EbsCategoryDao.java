package com.newhope.moneyfeed.goods.dal.dao.ebs;

import com.newhope.moneyfeed.goods.api.bean.ebs.EbsCategoryModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EbsCategoryDao extends BaseDao<EbsCategoryModel> {

    List<EbsCategoryModel> selectByOneCat(@Param("catId")String catId);

    List<EbsCategoryModel> selectByTwoCat(@Param("oneCatId")String oneCatId);

    List<EbsCategoryModel> selectByThreeCat(@Param("oneCatId")String oneCatId,@Param("twoCatId")String twoCatId);

    List<EbsCategoryModel> selectByFourCat(@Param("oneCatId")String oneCatId,@Param("twoCatId")String twoCatId,@Param("threeCatId")String threeCatId);

}