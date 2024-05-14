package jpaswing;

import jpaswing.ui.MusicUI;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.awt.*;

@SpringBootApplication
public class SwingApplication {

    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new SpringApplicationBuilder(SwingApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        EventQueue.invokeLater(() -> {
            // obtenemos el objeto MusicUI a través de Spring de tal forma que inyecte los componentes en el constructor
            MusicUI musicUI = context.getBean(MusicUI.class);
            musicUI.setVisible(true);
        });
    }
}
