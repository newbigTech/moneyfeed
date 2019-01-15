package com.newhope.moneyfeed.common.util;

import com.newhope.moneyfeed.common.vo.EnumConstResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 2018/12/25.
 */
public class EnumUtil {


    //枚举类包路径

    private final  static Logger logger= LoggerFactory.getLogger(EnumUtil.class);

    public static List<EnumConstResult> getEnumByName(String packagePath,String name) throws Exception {

        final List<Object> enums = getEnums(packagePath,name);

        if(CollectionUtils.isEmpty(enums)){
            return null;
        }
        List<EnumConstResult> enumInfo = new ArrayList<>(enums.size());
        for (Object o : enums) {
            EnumConstResult e = new EnumConstResult();
            BeanUtils.copyProperties(o, e);
            enumInfo.add(e);
        }
        return enumInfo;
    }

    /**
     * 获取某个枚举类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    private  static List<Object> getEnums(String packagePath,String name) throws Exception {
        List<Class<?>> clazzList = null;
        try {
            String pattern = "^[a-zA-Z.]+" + name + "$";
            clazzList = PackageUtil.getClass(packagePath, pattern);
        } catch (ClassNotFoundException e) {
            logger.error("[SysParamsService.getEnums]:获取枚举类异常", e);
            throw new Exception("获取枚举类异常");
        }
        if (CollectionUtils.isNotEmpty(clazzList)) {
            List<Object> enumList = new ArrayList<>();
            for (Object o : clazzList.get(0).getEnumConstants()) {
                enumList.add(o);
            }
            return enumList;
        }
        return null;
    }


}