package paiad.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import paiad.pojo.po.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
