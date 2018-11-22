package javacoreadvanced.lesson4.config;


import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.logging.*;


/**
 * Класс LoggerChat. В перспективе бужет записывать стеки ошибок и прочие системные сообщения.
 * По факту на 17.11.2018 не реализован в рамках данного чата
 * */
@NoArgsConstructor
public final class LoggerChat {

    public static final Logger LOGGER = Logger.getLogger("");
    {
        LOGGER.setLevel(Level.SEVERE);
        Handler handler = null;
        try {
            handler = new FileHandler("logSendMessage.log", 100000,1,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(handler);
    }
}
