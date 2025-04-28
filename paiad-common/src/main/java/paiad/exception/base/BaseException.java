package paiad.exception.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:AD
 * @Date:2024/04/09, 10:00:40
 * 业务异常
 */

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class BaseException extends RuntimeException {
    private int code;
    private String message;
}
