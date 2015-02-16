package se.rapid.lostpassword;

import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import org.springframework.validation.Validator;

public class LostPasswordFormValidator implements Validator
{

	private static final Logger logger = LoggerFactory.getLogger(LostPasswordFormValidator.class);

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz)
	{
		return LostPasswordForm.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors)
	{
		logger.debug("Validating lost password form.");
		LostPasswordForm form = (LostPasswordForm) obj;
		// Insure that a value with specified.
		rejectIfEmptyOrWhitespace(errors, "username", "error.username.empty");
		// Insure the inputs don't contain any illegal characters.
		if (!isAlphanumeric(form.getUsername()))
			errors.rejectValue("username", "error.username.illegal.chars");
		if (isNotBlank(form.getUsername()) && form.getUsername().length() < 4)
			errors.rejectValue("username", "error.username.too.short");
	}
}