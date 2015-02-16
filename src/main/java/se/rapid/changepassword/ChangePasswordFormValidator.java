package se.rapid.changepassword;

import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import org.springframework.validation.Validator;

import se.rapid.domain.Customer;
import se.rapid.security.BCrypt;

public class ChangePasswordFormValidator implements Validator
{

	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordFormValidator.class);

	private final Customer user;

	public ChangePasswordFormValidator(Customer user)
	{
		this.user = user;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz)
	{
		return ChangePasswordForm.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors)
	{
		logger.debug("Validating change password form.");
		ChangePasswordForm form = (ChangePasswordForm) obj;
		// Insure that a value with specified.
		rejectIfEmptyOrWhitespace(errors, "original", "error.original.password.empty");
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
		// Insure the specified original password actually matches the
		// performer's current password.
		if (isNotBlank(form.getPassword()))
		{
			if (!BCrypt.checkpw(form.getOriginal(), user.getPassword()))
			{
				errors.rejectValue("original", "error.original.password.mismatch");
			}
		}
	}
}