package paiad.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页返回结果类
 */
@Data
@AllArgsConstructor
public class PageResult implements Serializable {
    /**
     * 当前页码存在的记录数，子集个数
     */
    private Integer total;
    /**
     * 当前页的所有的记录数
     */
    private List<?> records;
}
