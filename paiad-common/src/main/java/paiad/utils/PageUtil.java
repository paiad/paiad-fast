package paiad.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import paiad.core.convert.Convert;
import paiad.result.PageResult;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil extends PageHelper {
    public static <T> Page<T> startPage(int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize);
    }

    public static <T> Page<T> startPage(int pageNum, int pageSize,String orderBy) {
        return PageHelper.startPage(pageNum, pageSize, orderBy);
    }

    /**
     * 获取分页信息
     * */
    public static <T> PageResult getPageInfo(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new PageResult(Convert.toInt(pageInfo.getTotal()), pageInfo.getList());
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
