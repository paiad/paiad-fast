package paiad.core.controller;

import paiad.core.convert.Convert;
import paiad.utils.PageUtil;
import paiad.utils.ServletUtil;

import static paiad.constant.PageConstant.PAGE_NUM;
import static paiad.constant.PageConstant.PAGE_SIZE;

/**
 * web层通用数据处理
 */
public class BaseController {
    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtil.startPage(Convert.toInt(ServletUtil.getParameter(PAGE_NUM)),
                           Convert.toInt(ServletUtil.getParameter(PAGE_SIZE)));
    }

}
