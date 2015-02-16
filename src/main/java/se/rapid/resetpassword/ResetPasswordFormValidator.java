package se.rapid.resetpassword;

import static org.apache.commons.lang.StringUtils.isAlphanumeric;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.rapid.changepassword.ChangePasswordForm;

public class ResetPasswordFormValidator implements Validator
{

	private static final Logger logger = LoggerFactory.getLogger(ResetPasswordFormValidator.class);

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz)
	{
		return ChangePasswordForm.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors)
	{
		logger.debug("Validating change password form.");
		ResetPasswordForm form = (ResetPasswordForm) obj;
		// Insure that a value with specified.
		rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty");
		rejectIfEmptyOrWhitespace(errors, "confirm", "error.confirm.empty");
		// Insure the inputs don't contain any illegal characters.
		if (!isAlphanumeric(form.getPassword()))
			errors.rejectValue("password", "error.password.illegal.chars");
		// Insure that the entries are within the valid length range.
		if (isNotBlank(form.getPassword()) && form.getPassword().length() < 6)
			errors.rejectValue("password", "error.password.too.short");
		// Insure the password and confirmation match.
		if (isNotBlank(form.getPassword()) && isNotBlank(form.getConfirm()))
		{
			if (!form.getPassword().equals(form.getConfirm()))
			{
				errors.rejectValue("password", "error.password.mismatch");
			}
		}
	}

}
