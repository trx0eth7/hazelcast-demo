package ru.trx.hazelcastdemo;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(entityManagerFactoryRef = "h2DataSourceEntityManagerFactory", transactionManagerRef = "h2DataSourceTransactionManager")
public class HazelcastDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemoApplication.class, args);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean h2DataSourceEntityManagerFactory(
            @Qualifier("h2DataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("ru.trx.hazelcastdemo")
                .persistenceUnit("Default")
                .build();
    }

    @Bean
    PlatformTransactionManager h2DataSourceTransactionManager(
            @Qualifier("h2DataSourceEntityManagerFactory") LocalContainerEntityManagerFactoryBean h2DataSourceEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(h2DataSourceEntityManagerFactory.getObject()));
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    HikariDataSource h2DataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }


    @Bean
    CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }
}
