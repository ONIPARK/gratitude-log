package gratitude;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("gratitude.gratitude_log.repository")
public class MyBatisConfig {

}
