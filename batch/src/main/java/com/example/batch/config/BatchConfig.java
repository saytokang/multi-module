package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch.pod.Tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;

	private final PlatformTransactionManager platformTransactionManager;

	private final Tasks tasks;

	@Bean
	public Job sampleJob() {
		return new JobBuilder("sample job", jobRepository).start(startStep()).build();
	}

	@Bean
	public Step startStep() {
		var builder = new StepBuilder("start", jobRepository);
		return builder.tasklet(tasks.task1(), platformTransactionManager).build();
	}

	private Tasklet tasklet() {
		return new Tasklet() {

			@Override
			// @Nullable
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				log.info("executed task");
				return RepeatStatus.FINISHED;
			}

		};
	}
}
