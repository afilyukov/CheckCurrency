package ru.afilyukov.CheckCurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients({"ru.afilyukov.CheckCurrency.Controllers", "ru.afilyukov.CheckCurrency.Client"})
@SpringBootApplication
public class CheckCurrencyApp {
	public static void main(String[] args) {
		SpringApplication.run(CheckCurrencyApp.class, args);
	}

}
