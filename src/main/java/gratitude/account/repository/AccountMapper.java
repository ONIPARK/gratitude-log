package gratitude.account.repository;

import gratitude.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface AccountMapper {
    //CREATE
    int insert(Account account);
    boolean existsByEmail(@Param("email") String email);
    boolean existsByNickname(@Param("nickname") String nickname);
    //READ
    Account findByEmail(@Param("email") String email);


}
