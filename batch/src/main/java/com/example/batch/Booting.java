package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Booting implements ApplicationRunner{

    private final JobLauncher jobLauncher;

	private final JdbcTemplate jdbcTemplate;

	private final Job personJob;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // batch job 실행.
		var param = new JobParameters();
		jobLauncher.run(personJob, param);

		// batch job 결과 확인
		// @formatter:off
		jdbcTemplate.queryForList("select * from person")
		.stream()
		.forEach(System.out::println);
		// @formatter:on
    }
    
}
