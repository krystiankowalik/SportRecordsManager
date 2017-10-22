package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.config.DatabaseConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        context.getBean(Control.class).run();

    }
}
