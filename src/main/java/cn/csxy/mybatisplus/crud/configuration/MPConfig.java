package cn.csxy.mybatisplus.crud.configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MPConfig {
    // 配置分页查询拦截器
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor Interceptor = new MybatisPlusInterceptor();
        Interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return  Interceptor;
    }
}
