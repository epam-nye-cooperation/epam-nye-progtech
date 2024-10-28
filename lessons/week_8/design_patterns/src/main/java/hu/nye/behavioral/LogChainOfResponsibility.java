package hu.nye.behavioral;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
abstract class BaseLogger extends LogChainOfResponsibility.Logger {
    private Level level = Level.DEBUG;
    private BaseLogger nextLogger;

    public void logMessage(Level level, String message) {
        if (this.level.ordinal() <= level.ordinal()) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);

}

class ConsoleLogger extends BaseLogger {
    @Override
    protected void write(String message) {
        System.out.println("Console::Logger: " + message);
    }
}

class ErrorLogger extends BaseLogger {
    @Override
    protected void write(String message) {
        System.out.println("Error::Logger: " + message);
    }
}

class FileLogger extends BaseLogger {
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

public class LogChainOfResponsibility {
    public static Logger getLogger() {
        return new ErrorLogger()
                .setLevel(Logger.Level.ERROR)
                .setNextLogger(new FileLogger().setLevel(Logger.Level.DEBUG)
                        .setNextLogger(new ConsoleLogger().setLevel(Logger.Level.INFO))
                );
    }

    public static abstract class Logger {
        public abstract void logMessage(BaseLogger.Level level, String message);

        public enum Level {DEBUG, INFO, ERROR}
    }
}
