package paiad.exception.argument;

import paiad.exception.base.BaseException;

/**
 * @author:AD
 * @Date:2024/08/19, 15:51:44
 */
public class ArgumentException extends BaseException {
    public ArgumentException(int code, String message){
        super(code, message);
    }
}
