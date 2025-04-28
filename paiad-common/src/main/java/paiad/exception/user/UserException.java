package paiad.exception.user;

import paiad.exception.base.BaseException;

/**
 * @author:AD
 * @Date:2024/08/05, 23:38:57
 */
public class UserException extends BaseException {
    public UserException(int code, String message) {
        super(code, message);
    }
}
