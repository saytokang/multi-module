package com.example.batch.pod;

import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Tasks {
    public Tasklet task1() {
		var adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this);
		adapter.setTargetMethod("run");
		return adapter;
	}

	private void run() {
		log.info("------ run -------");
	}
}
