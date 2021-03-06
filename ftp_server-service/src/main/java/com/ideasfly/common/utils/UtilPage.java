package com.ideasfly.common.utils;

import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 对一个list集合进行分页操作
 */
public class UtilPage {

	/**
	 * 私有构造器
	 */
	private UtilPage() {

	}
    /**
     * @param list      数据
     * @param pageIndex 页码
     * @param pageSize  分页条数
     * @return List<T> 分页数据
     */
    public static <T> List<T> getPageList(List<T> list, int pageIndex, int pageSize) {

        List<T> subList = null;
        if (!CollectionUtils.isEmpty(list)) {
            int total = list.size();
            int pageCount = 0;
            int m = total % pageSize;
            total = list.size();
            if (m > 0) {
                pageCount = total / pageSize + 1;
            } else {
                pageCount = total / pageSize;
            }

            if (pageIndex == pageCount) {
                subList = list.subList((pageIndex - 1) * pageSize, total);
            } else {
                subList = list.subList((pageIndex - 1) * pageSize, pageSize * pageIndex);
            }
        }
        return subList;
    }


	/**
	 * 计算出起始值
	 * 
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            分页条数
	 * @param total
	 *            总行数
	 * @return 起始位置 说明：mysql数据库start是以0开始的
	 */
	public static int calcStart(Integer pageIndex, Integer pageSize, int total) {
		int start = 0;
		if (pageIndex!=null && pageSize!=null && pageSize>0){
			int totalPage = (total % pageSize == 0) ? total / pageSize : (total / pageSize + 1);
			if (pageIndex < 1 || totalPage < 1) {
				start = 0;
			} else if (pageIndex > totalPage) {
				start = (totalPage - 1) * pageSize;
			} else {
				start = (pageIndex - 1) * pageSize;
			}
		}
		return start;
	}


}
