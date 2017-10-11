package gov.cms.qpp.conversion.api.controllers.v1;

import gov.cms.qpp.conversion.api.exceptions.QppValidationException;
import gov.cms.qpp.conversion.api.model.Constants;
import gov.cms.qpp.conversion.api.services.AuditService;
import gov.cms.qpp.conversion.model.error.AllErrors;
import gov.cms.qpp.conversion.model.error.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Modify the controller to send back different responses for exceptions
 */
@ControllerAdvice
public class ExceptionHandlerControllerV1 extends ResponseEntityExceptionHandler {
	private static final Logger API_LOG = LoggerFactory.getLogger(Constants.API_LOG);

	@Autowired
	private AuditService auditService;

	/**
	 * "Catch" the {@link TransformException}.
	 * Return the {@link AllErrors} with an HTTP status 422.
	 *
	 * @param exception The TransformException that was "caught".
	 * @param request The request.
	 * @return The AllErrors dto that details the TransformException.
	 */
	@ExceptionHandler(TransformException.class)
	@ResponseBody
	protected ResponseEntity<AllErrors> handleTransformException(TransformException exception, WebRequest request) {
		return makeResponse(exception);
	}

	/**
	 * "Catch" the {@link QppValidationException}.
	 * Return the {@link AllErrors} with an HTTP status 422.
	 *
	 * @param exception The QppValidationException that was "caught".
	 * @param request The request.
	 * @return The AllErrors dto that details the QppValidationException.
	 */
	@ExceptionHandler(QppValidationException.class)
	@ResponseBody
	protected ResponseEntity<AllErrors> handleQppValidationException(QppValidationException exception, WebRequest request) {
		return makeResponse(exception);
	}

	private ResponseEntity<AllErrors> makeResponse(TransformException exception) {
		API_LOG.error("Problem during conversion: ", exception);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		return new ResponseEntity<>(exception.getDetails(), httpHeaders,
				HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
