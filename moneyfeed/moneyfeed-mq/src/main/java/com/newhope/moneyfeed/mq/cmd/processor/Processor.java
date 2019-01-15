package com.newhope.moneyfeed.mq.cmd.processor;

import com.newhope.moneyfeed.mq.cmd.Command;
import com.newhope.moneyfeed.mq.exception.CommandProcessException;

public interface Processor {
	public ExecutionResult execute(Command command) throws CommandProcessException;
}
