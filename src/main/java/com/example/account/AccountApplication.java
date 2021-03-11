package com.example.account;

import com.example.account.gateways.AccountGateway;
import com.example.account.usecases.CreateAccount;
import com.example.account.usecases.DoOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Bean
	public CreateAccount createAccount(AccountGateway accountGateway) {
		return new CreateAccount(accountGateway);
	}

	@Bean
	public DoOperation doOperation(AccountGateway accountGateway) {
		return new DoOperation(accountGateway);
	}

}
