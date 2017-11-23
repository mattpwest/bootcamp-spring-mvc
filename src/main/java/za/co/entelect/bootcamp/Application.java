package za.co.entelect.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import za.co.entelect.bootcamp.cli.CommandLineInterface;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		CommandLineInterface cli = ctx.getBean(CommandLineInterface.class);
		cli.printPublishersReport();
	}
}
