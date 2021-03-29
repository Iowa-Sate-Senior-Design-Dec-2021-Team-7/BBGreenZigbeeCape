package webhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code webHost} startup class
 */
@SpringBootApplication
public class WebHostApplication {
    /**
     * Main method for {@code WebHostApplication}
     * <br>
     * Call to start the application
     * @param args
     *      Any {@code String} arguments to specify runtime rules
     *      <br>
     *      Currently, there are none
     */
    public static void main(String[] args) { SpringApplication.run(WebHostApplication.class, args); }
}
