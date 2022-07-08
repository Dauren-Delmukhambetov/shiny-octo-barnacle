package org.serverless.template;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public abstract class SqsEventHandler extends BaseHandler<SQSEvent.SQSMessage, Void, SQSEvent, Void>{

    @Override
    public Void handleRequest(SQSEvent input, Context context) {
        try {
            log(context, "Starting processing SQS event of size %d", input.getRecords().size());

            for (SQSEvent.SQSMessage message : input.getRecords()) {
                doHandleRequest(message);
            }

            log(context, "Completing processing SQS event of size %d", input.getRecords().size());
        } catch (Exception e) {
            log(context, "Error occurred while processing SQS event %s: %s", "", e.getMessage());
        }
        return null;
    }
}
