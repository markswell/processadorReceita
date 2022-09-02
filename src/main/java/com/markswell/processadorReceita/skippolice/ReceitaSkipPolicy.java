package com.markswell.processadorReceita.skippolice;

import org.springframework.stereotype.Component;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;

@Component
public class ReceitaSkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        System.out.println("ERRO: " + (throwable.getMessage()));
        return true;
    }
}
