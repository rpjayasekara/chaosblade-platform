package com.alibaba.chaosblade.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author randika
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Problem creating experiment")
public class ErrorInExperimentCreationException extends RuntimeException {

}
