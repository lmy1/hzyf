package com.cd.uap.exception.handler;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.cd.uap.bean.Response;
import com.cd.uap.bean.ResultCode;
import com.cd.uap.exception.InspectionException;
import com.cd.uap.exception.LogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class ControllerExceptionHandler {
	private Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response handleException(Exception e) {
		if (e.getCause() != null) {
			log.error(e.getCause().getMessage());
			e.printStackTrace();
			return new Response(1, ResultCode.FAILED_SYSTEM.appendMsg(e.getCause().getMessage()), null);
		} else {
			log.error(e.getMessage());
			e.printStackTrace();
			return new Response(1, ResultCode.FAILED_SYSTEM.appendMsg(e.getMessage()), null);
		}
	}

	@ExceptionHandler(InspectionException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response handleInspectionException(InspectionException e) {
		log.error(e.getMessage());
		return new Response(1, ResultCode.FAILED_INSPECTION.appendMsg(e.getMessage()), null);
	}

	@ExceptionHandler(LogicException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response handleLogicException(LogicException e) {
		log.error(e.getMessage());
		return new Response(1, ResultCode.FAILED_LOGIC.appendMsg(e.getMessage()), null);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response handleConstraintViolationException(ConstraintViolationException e) {
		log.error(e.getMessage());

		Set<ConstraintViolation<?>> set = e.getConstraintViolations();
		StringBuilder msg = new StringBuilder();
		if (set != null && set.size() > 0) {
			for (ConstraintViolation<?> constraintViolation : set) {
				msg.append(constraintViolation.getMessage());
				msg.append("  ");
			}
		}

		return new Response(1, ResultCode.FAILED_VALIDATION.appendMsg(msg.toString()), null);
	}

}