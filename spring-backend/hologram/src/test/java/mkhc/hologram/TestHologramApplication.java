package mkhc.hologram;

import org.springframework.boot.SpringApplication;

public class TestHologramApplication {

	public static void main(String[] args) {
		SpringApplication.from(HologramApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
