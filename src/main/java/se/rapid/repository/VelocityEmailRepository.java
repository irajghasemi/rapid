package se.rapid.repository;

import org.springframework.stereotype.Repository;

import se.rapid.domain.Customer;

@Repository
public interface VelocityEmailRepository
{
	void sendLostPasswordEmail(final Customer user, final String action);
}